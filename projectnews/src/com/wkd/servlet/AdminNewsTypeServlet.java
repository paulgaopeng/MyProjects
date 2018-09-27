package com.wkd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wkd.entity.NewsType;
import com.wkd.service.NewsTypeService;
import com.wkd.util.JsonUtil;

/**
 * �������� Servlet
 * @author gaopeng
 *
 */
public class AdminNewsTypeServlet extends HttpServlet {
	private NewsTypeService newsTypeService = new NewsTypeService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String method=request.getParameter("method");
		if(method.equals("getNewsTypeList")){
			getNewsTypeList(request,response);
		}
	}

	//�첽���� --���������б�
	private void getNewsTypeList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ���� �б�
		List<NewsType> newsTypeList = newsTypeService.queryNewsTypeList();
	
		//��list -->תΪJSONStr
		String jsonStr = JsonUtil.getJsonString4List(newsTypeList);
		System.out.println(jsonStr);
		
		JsonUtil.ajaxResponse(response, jsonStr);
	}

}
