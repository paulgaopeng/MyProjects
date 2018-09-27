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
 * 新闻主题 Servlet
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

	//异步加载 --新闻主题列表
	private void getNewsTypeList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询主题 列表
		List<NewsType> newsTypeList = newsTypeService.queryNewsTypeList();
	
		//将list -->转为JSONStr
		String jsonStr = JsonUtil.getJsonString4List(newsTypeList);
		System.out.println(jsonStr);
		
		JsonUtil.ajaxResponse(response, jsonStr);
	}

}
