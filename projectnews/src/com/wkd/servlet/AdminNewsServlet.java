package com.wkd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wkd.entity.PageData;

public class AdminNewsServlet extends HttpServlet {
	private PageData pageData = new PageData();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method=request.getParameter("method");
		if(method.equals("newsList")){
			newsList(request,response);
		}
	}

	/**�����б�*/
	private void newsList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���÷�ҳʵ������
		//pageData.setMaxCount(maxCount);
		
		//���ݷ�ҳʵ����� --��ѯ���������б�
	
		
		
		//�������������� 
		//request.setAttribute("pageData",pageData);
		//request.setAttribute("newsList",newsList);


		//����news_manage.jsp
		request.getRequestDispatcher("/admin/news_manage.jsp").forward(request,response);
	}

}
