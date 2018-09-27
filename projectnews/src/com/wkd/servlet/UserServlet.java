package com.wkd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wkd.entity.UserInfo;
import com.wkd.service.UserService;

/**
 * 用户Servlet --处理用户相关的所有请求
 */
public class UserServlet extends HttpServlet {
	//用户业务对象
	private UserService userService = new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取请求的参数
		String method=request.getParameter("method");
		if(method.equals("login")){
			login(request,response);
		}else if(method.equals("logout")){
			logout(request,response);
		}
	}

	/**登录*/
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username==null || "".equals(username)){
			request.setAttribute("error","用户名不能为空!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
			return;
		}
		
		if(password==null || "".equals(password)){
			request.setAttribute("error","密码不能为空!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
			return;
		}
		
		//访问业务对象,获取用户名对应的用户对象
		UserInfo user = userService.queryUserByName(username);
		if(user==null){
			//用户名不存在
			request.setAttribute("error","用户名不存在!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}else if(!user.getPassword().equals(password)){
			//密码错误
			request.setAttribute("error","密码错误!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}else{
			//登录成功
			//获取是否记住我
			String isSave = request.getParameter("isSave");
			//创建Cookie
			Cookie ck1 = new Cookie("username",username);
			Cookie ck2 = new Cookie("password",password);
			if(isSave != null && isSave.equals("on")){
				//记住用户和密码
				ck1.setMaxAge(60*60*24*30);  //一个月
				ck2.setMaxAge(60*60*24);  //10
			}else{
				//删除Cookie
				ck1.setMaxAge(0);
				ck2.setMaxAge(0);
			}
			//发送到客户端
			response.addCookie(ck1);
			response.addCookie(ck2);
			
			//将当前登录的用户存入会话作用域
			/*HttpSession ses = request.getSession();
			ses.setAttribute("loginUser", user);*/
			request.getSession().setAttribute("loginUser",user);
			
			request.getRequestDispatcher("/admin/manager_index.jsp").forward(request,response);
		}
	}
	
	/**退出*/
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.getSession().invalidate(); //使当前会话失效
			response.sendRedirect("index.jsp"); //返回首页
	}

}
