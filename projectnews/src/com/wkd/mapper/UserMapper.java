package com.wkd.mapper;

import java.util.List;
import java.util.Map;

import com.wkd.entity.UserInfo;

public interface UserMapper {
	public UserInfo queryUserByName(String username);

	public List<UserInfo> queryUserList();

	public Integer addUser(UserInfo user);

	public Integer queryMaxCount(Map<String, String> mapParams);

	public List<UserInfo> queryUserList(Map<String, Object> mapParams);

	public Integer deleteUserById(int userId);

	public Integer deleteUserBatch(String[] idsArr);
	
}
