package com.wkd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wkd.entity.UserInfo;
import com.wkd.util.DBUtil;

public class UserMapper2 {
	private SqlSession session = null;
	
	//根据用户名查询用户
	public UserInfo queryUserByName(String username) {
		session = DBUtil.getSession();
		UserInfo user = null;
		try {
			user = session.selectOne("com.wkd.mapper.UserMapper.queryUserByName", username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(session);
		}
			
		return user;
	}

	public List<UserInfo> queryUserList() {
		List<UserInfo> userList = null;
		session = DBUtil.getSession();
		try {
			userList = session.selectList("com.wkd.mapper.UserMapper.queryUserList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(session);
		}
		
		return userList;
	}

	public boolean addUser(UserInfo user) {
		session = DBUtil.getSession();
		try {
			int rows = session.insert("com.wkd.mapper.UserMapper.addUser", user);
			session.commit(); //提交事务
			if(rows>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			DBUtil.close(session);
		}
		return false;
	}

	public int queryMaxCount(Map<String,String> paramsMap) {
		session = DBUtil.getSession();
		int count = 0;
		try {
			 count = session.selectOne("com.wkd.mapper.UserMapper.queryMaxCount",paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(session);
		}
		return count;
	}

	public List<UserInfo> queryUserList(Map<String, Object> mapParams) {
		List<UserInfo> userList = null;
		session = DBUtil.getSession();
		try {
			userList = session.selectList("com.wkd.mapper.UserMapper.queryUserList",mapParams);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(session);
		}
		
		return userList;
	}

	public boolean deleteUserById(int userId) {
		session = DBUtil.getSession();
		try {
			int rows = session.delete("com.wkd.mapper.UserMapper.deleteUserById", userId);
			session.commit(); //提交事务
			if(rows>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			DBUtil.close(session);
		}
		return false;
	}

	public boolean deleteUserBatch(String[] idsArr) {
		session = DBUtil.getSession();
		try {
			int rows = session.delete("com.wkd.mapper.UserMapper.deleteUserBatch", idsArr);
			session.commit(); //提交事务
			if(rows>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			DBUtil.close(session);
		}
		return false;
	}
	
}
