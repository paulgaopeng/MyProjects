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
 * Ȩ�޿��ƹ�����
 * @author gaopeng
 *
 */
public class RoleFilter implements Filter {

	public void destroy() {
	
	}

	//���˴���
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		//��鵱ǰ����ĻỰ���������Ƿ��е�¼���û�
		HttpSession ses = request.getSession();
		
		UserInfo loginUser = (UserInfo)ses.getAttribute("loginUser");
		if(loginUser == null){
			request.setAttribute("error","�Բ���,���ȵ�¼ϵͳ!");
			request.getRequestDispatcher("/index.jsp").forward(request,resp);
		}else if(!loginUser.getIsAdmin()){
			request.setAttribute("error","�Բ���,��û��Ȩ�޽��иò���!");
			request.getRequestDispatcher("/index.jsp").forward(request,resp);
		}else{
			//����Ŀ����Դ
			chain.doFilter(req, resp);
		}
				
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
