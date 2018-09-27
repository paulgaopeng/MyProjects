package com.wkd.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {
	//会话工厂对象
	private static SqlSessionFactory sqlSessionFactory = null;
	
	//初始化会话工厂对象
	private static void init(){
		try {
			String resource = "mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取一个sqlSession对象
	public static SqlSession getSession(){
		if(sqlSessionFactory == null){
			init(); //初始化
		}
		return sqlSessionFactory.openSession();
	}
	
	public static void close(SqlSession session){
		if(session!=null){
			session.close();
		}
	}
	
	public static void main(String[] args) {
		SqlSession session = DBUtil.getSession();
		
		System.out.println(session!=null ? "OK" :"error");
	}
}
