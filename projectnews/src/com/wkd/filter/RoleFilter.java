package com.wkd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wkd.entity.UserInfo;

/**
 * 权限控制过滤器
 * @author gaopeng
 *
 */
public class RoleFilter implements Filter {

	public void destroy() {
	
	}

	//过滤处理
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		//检查当前请求的会话作用域中是否有登录的用户
		HttpSession ses = request.getSession();
		
		UserInfo loginUser = (UserInfo)ses.getAttribute("loginUser");
		if(loginUser == null){
			request.setAttribute("error","对不起,请先登录系统!");
			request.getRequestDispatcher("/index.jsp").forward(request,resp);
		}else if(!loginUser.getIsAdmin()){
			request.setAttribute("error","对不起,您没有权限进行该操作!");
			request.getRequestDispatcher("/index.jsp").forward(request,resp);
		}else{
			//到达目标资源
			chain.doFilter(req, resp);
		}
				
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
