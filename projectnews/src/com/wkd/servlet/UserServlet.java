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
 * �û�Servlet --�����û���ص���������
 */
public class UserServlet extends HttpServlet {
	//�û�ҵ�����
	private UserService userService = new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ����Ĳ���
		String method=request.getParameter("method");
		if(method.equals("login")){
			login(request,response);
		}else if(method.equals("logout")){
			logout(request,response);
		}
	}

	/**��¼*/
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username==null || "".equals(username)){
			request.setAttribute("error","�û�������Ϊ��!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
			return;
		}
		
		if(password==null || "".equals(password)){
			request.setAttribute("error","���벻��Ϊ��!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
			return;
		}
		
		//����ҵ�����,��ȡ�û�����Ӧ���û�����
		UserInfo user = userService.queryUserByName(username);
		if(user==null){
			//�û���������
			request.setAttribute("error","�û���������!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}else if(!user.getPassword().equals(password)){
			//�������
			request.setAttribute("error","�������!");
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}else{
			//��¼�ɹ�
			//��ȡ�Ƿ��ס��
			String isSave = request.getParameter("isSave");
			//����Cookie
			Cookie ck1 = new Cookie("username",username);
			Cookie ck2 = new Cookie("password",password);
			if(isSave != null && isSave.equals("on")){
				//��ס�û�������
				ck1.setMaxAge(60*60*24*30);  //һ����
				ck2.setMaxAge(60*60*24);  //10
			}else{
				//ɾ��Cookie
				ck1.setMaxAge(0);
				ck2.setMaxAge(0);
			}
			//���͵��ͻ���
			response.addCookie(ck1);
			response.addCookie(ck2);
			
			//����ǰ��¼���û�����Ự������
			/*HttpSession ses = request.getSession();
			ses.setAttribute("loginUser", user);*/
			request.getSession().setAttribute("loginUser",user);
			
			request.getRequestDispatcher("/admin/manager_index.jsp").forward(request,response);
		}
	}
	
	/**�˳�*/
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.getSession().invalidate(); //ʹ��ǰ�ỰʧЧ
			response.sendRedirect("index.jsp"); //������ҳ
	}

}
