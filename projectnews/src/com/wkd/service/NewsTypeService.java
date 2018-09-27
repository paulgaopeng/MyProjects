package com.wkd.service;

import java.util.List;

import com.wkd.entity.NewsType;
import com.wkd.mapper.NewsTypeMapper;

public class NewsTypeService {
	private NewsTypeMapper newsTypeMapper = null;
	
	public NewsTypeService() {
		newsTypeMapper = new NewsTypeMapper();
	}
	
	public List<NewsType> queryNewsTypeList() {
		
		return newsTypeMapper.queryNewsTypeList();
	}
	
}
