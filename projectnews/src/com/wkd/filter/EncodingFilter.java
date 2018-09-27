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
	
	//执行过滤处理
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		//获取配置文件中的字符集名称
		String charset = fc.getInitParameter("charset");
		if(charset==null){
			charset="UTF-8";
		}
		req.setCharacterEncoding(charset);
		
		//将当前请求转发给过滤器链中的下一个过滤器,如果过滤器链中没有其他的过滤器,则请求到达目标资源
		chain.doFilter(req, resp);
	}

	public void destroy() {
		
	}
}
