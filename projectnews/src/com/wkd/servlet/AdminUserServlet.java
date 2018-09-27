package com.wkd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wkd.entity.PageData;
import com.wkd.entity.UserInfo;
import com.wkd.service.UserService;

/**
 * 处理用户管理相关的请求
 */
public class AdminUserServlet extends HttpServlet {
	//用户业务对象
	private UserService userService = new UserService();
	//创建分页实体对象
	PageData pageData = new PageData();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method=request.getParameter("method");
		if(method.equals("userList")){
			userList(request,response);
		}else if(method.equals("gotoAddUser")){
			gotoAddUser(request,response);
		}else if(method.equals("addUser")){
			addUser(request,response);
		}else if(method.equals("deleteUserById")){
			deleteUserById(request,response);
		}else if(method.equals("deleteUserBatch")){
			deleteUserBatch(request,response);
		}
	}


	/**用户列表*/
	private void userList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//接收查询的条件类别Id 与查询的关键 字
		String queryTypeId = request.getParameter("queryTypeId");
		String queryKeyWords = request.getParameter("queryKeyWords");
		
		
		//获取请求参数currPage
		String currPage = request.getParameter("currPage");
		
		//设置总记录数
		int maxCount = userService.queryMaxCount(queryTypeId,queryKeyWords);
		pageData.setMaxCount(maxCount);
		
		if(currPage != null && !"".equals(currPage)){
			pageData.setCurrPage(Integer.parseInt(currPage));  //改变当前页码
		}
		
		//查询用户列表
		List<UserInfo> userList = userService.queryUserList(pageData,queryTypeId,queryKeyWords);
		
		//存入请求作用域 
		request.setAttribute("userList", userList);
		request.setAttribute("pageData",pageData);
		request.setAttribute("queryTypeId", queryTypeId);
		request.setAttribute("queryKeyWords", queryKeyWords);
		
		request.getRequestDispatcher("/admin/user_manage.jsp").forward(request,response);
	}

	/**准备进入用户添加页面*/
	private void gotoAddUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
	}
	
	/**执行用户添加操作*/
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//获取当前会话中的token
		HttpSession ses = request.getSession();
		String token = String.valueOf(ses.getAttribute("token"));
		
		//获取表单中的token
		String formToken = request.getParameter("formToken");
		if(token!=null && token.equals(formToken)){
			//从会话中移除token
			ses.removeAttribute("token");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			String gender = request.getParameter("gender");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String isAdmin = request.getParameter("isAdmin");
			//创建对象--封装要添加的用户数据
			UserInfo user = new UserInfo(username,password,realname,gender.equals("1") ? true : false,
					telephone,email,isAdmin.equals("1")? true : false);
			
			int rows = userService.addUser(user);
			boolean flag = rows>0 ? true:false;
			//设置作用域消息
			request.setAttribute("msg", (flag ? "添加成功!" : "添加失败!"));
		}
		
		//进入用户管理页面
		userList(request,response);
	}
	
	/**根据Id删除用户*/
	private void deleteUserById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取要删除的userId
		int userId = Integer.parseInt(request.getParameter("userId"));
		int rows = userService.deleteUserById(userId);
		boolean flag = rows>0 ? true:false;
		//设置作用域消息
		request.setAttribute("msg", (flag ? "删除成功!" : "删除失败!"));
		//进入用户管理页面
		userList(request,response);
	}
	
	/**批量删除用户*/
	private void deleteUserBatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("ids");
		int rows = userService.deleteUserBatch(ids);
		boolean flag = rows>0 ? true:false;
		//设置作用域消息
		request.setAttribute("msg", (flag ? "批量删除成功!" : "批量删除失败!"));
		//进入用户管理页面
		userList(request,response);
	}

}
