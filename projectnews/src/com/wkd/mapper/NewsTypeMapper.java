package com.wkd.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wkd.entity.NewsType;
import com.wkd.util.DBUtil;

public class NewsTypeMapper {
	private SqlSession session = null;
	
	/*
	 * 查询新闻主题 列表
	 */
	public List<NewsType> queryNewsTypeList() {
		List<NewsType> newsTypeList = null;
		session = DBUtil.getSession();
		try {
			newsTypeList = session.selectList("com.wkd.mapper.NewsTypeMapper.queryNewsTypeList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(session);
		}
		
		return newsTypeList;
	}
	
}
