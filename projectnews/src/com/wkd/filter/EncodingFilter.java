package com.wkd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	private FilterConfig fc = null;
	
	public void init(FilterConfig fc) throws ServletException {
		this.fc = fc;
	}
	
	//ִ�й��˴���
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		//��ȡ�����ļ��е��ַ�������
		String charset = fc.getInitParameter("charset");
		if(charset==null){
			charset="UTF-8";
		}
		req.setCharacterEncoding(charset);
		
		//����ǰ����ת�������������е���һ��������,�������������û�������Ĺ�����,�����󵽴�Ŀ����Դ
		chain.doFilter(req, resp);
	}

	public void destroy() {
		
	}
}
