package com.wkd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wkd.entity.PageData;
import com.wkd.entity.UserInfo;
import com.wkd.mapper.UserMapper;
import com.wkd.util.DBUtil;

/**
 * 用户业务类
 */
public class UserService {
	private UserMapper userMapper = null;
//	private CommentMapper commentMapper= null;
	
	public UserService() {
		userMapper = DBUtil.getSession().getMapper(UserMapper.class);
	}
	
	/**根据用户名查询用户*/
	public UserInfo queryUserByName(String username) {
		
		return userMapper.queryUserByName(username);
	}

	public List<UserInfo> queryUserList() {
		return userMapper.queryUserList();
	}

	public Integer addUser(UserInfo user) {
		
		return userMapper.addUser(user);
	}

	//查询总记录数
	public int queryMaxCount(String queryTypeId, String queryKeyWords) {
		Map<String,String> mapParams = new HashMap<String,String>();
		mapParams.put("queryTypeId", queryTypeId);
		mapParams.put("queryKeyWords", queryKeyWords);
		return userMapper.queryMaxCount(mapParams);
	}

	//根据分页实体查询列表
	public List<UserInfo> queryUserList(PageData pageData,String queryTypeId,
			String queryKeyWords) {
		//起始索引号
		int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
		//页大小
		int pageSize = pageData.getPageSize();
		
		//封装到Map
		Map<String,Object> mapParams = new HashMap<String,Object>();
		mapParams.put("startIndex", startIndex);
		mapParams.put("pageSize", pageSize);
		mapParams.put("queryTypeId", queryTypeId);
		mapParams.put("queryKeyWords", queryKeyWords);
		
		return userMapper.queryUserList(mapParams);
	}

	public Integer deleteUserById(int userId) {
		//先删除用户评论
		
		//再删除用户
		
		return userMapper.deleteUserById(userId);
	}

	public Integer deleteUserBatch(String ids) {
		//"20,21"
		String[] idsArr = ids.split(",");
		return userMapper.deleteUserBatch(idsArr);
	}


}
