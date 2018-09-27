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
 * �����û�������ص�����
 */
public class AdminUserServlet extends HttpServlet {
	//�û�ҵ�����
	private UserService userService = new UserService();
	//������ҳʵ�����
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


	/**�û��б�*/
	private void userList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���ղ�ѯ���������Id ���ѯ�Ĺؼ� ��
		String queryTypeId = request.getParameter("queryTypeId");
		String queryKeyWords = request.getParameter("queryKeyWords");
		
		
		//��ȡ�������currPage
		String currPage = request.getParameter("currPage");
		
		//�����ܼ�¼��
		int maxCount = userService.queryMaxCount(queryTypeId,queryKeyWords);
		pageData.setMaxCount(maxCount);
		
		if(currPage != null && !"".equals(currPage)){
			pageData.setCurrPage(Integer.parseInt(currPage));  //�ı䵱ǰҳ��
		}
		
		//��ѯ�û��б�
		List<UserInfo> userList = userService.queryUserList(pageData,queryTypeId,queryKeyWords);
		
		//�������������� 
		request.setAttribute("userList", userList);
		request.setAttribute("pageData",pageData);
		request.setAttribute("queryTypeId", queryTypeId);
		request.setAttribute("queryKeyWords", queryKeyWords);
		
		request.getRequestDispatcher("/admin/user_manage.jsp").forward(request,response);
	}

	/**׼�������û����ҳ��*/
	private void gotoAddUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
	}
	
	/**ִ���û���Ӳ���*/
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡ��ǰ�Ự�е�token
		HttpSession ses = request.getSession();
		String token = String.valueOf(ses.getAttribute("token"));
		
		//��ȡ���е�token
		String formToken = request.getParameter("formToken");
		if(token!=null && token.equals(formToken)){
			//�ӻỰ���Ƴ�token
			ses.removeAttribute("token");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			String gender = request.getParameter("gender");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String isAdmin = request.getParameter("isAdmin");
			//��������--��װҪ��ӵ��û�����
			UserInfo user = new UserInfo(username,password,realname,gender.equals("1") ? true : false,
					telephone,email,isAdmin.equals("1")? true : false);
			
			int rows = userService.addUser(user);
			boolean flag = rows>0 ? true:false;
			//������������Ϣ
			request.setAttribute("msg", (flag ? "��ӳɹ�!" : "���ʧ��!"));
		}
		
		//�����û�����ҳ��
		userList(request,response);
	}
	
	/**����Idɾ���û�*/
	private void deleteUserById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪɾ����userId
		int userId = Integer.parseInt(request.getParameter("userId"));
		int rows = userService.deleteUserById(userId);
		boolean flag = rows>0 ? true:false;
		//������������Ϣ
		request.setAttribute("msg", (flag ? "ɾ���ɹ�!" : "ɾ��ʧ��!"));
		//�����û�����ҳ��
		userList(request,response);
	}
	
	/**����ɾ���û�*/
	private void deleteUserBatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("ids");
		int rows = userService.deleteUserBatch(ids);
		boolean flag = rows>0 ? true:false;
		//������������Ϣ
		request.setAttribute("msg", (flag ? "����ɾ���ɹ�!" : "����ɾ��ʧ��!"));
		//�����û�����ҳ��
		userList(request,response);
	}

}
