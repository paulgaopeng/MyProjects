package com.wkd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wkd.entity.PageData;
import com.wkd.entity.UserInfo;
import com.wkd.mapper.UserMapper;
import com.wkd.util.DBUtil;

/**
 * �û�ҵ����
 */
public class UserService {
	private UserMapper userMapper = null;
//	private CommentMapper commentMapper= null;
	
	public UserService() {
		userMapper = DBUtil.getSession().getMapper(UserMapper.class);
	}
	
	/**�����û�����ѯ�û�*/
	public UserInfo queryUserByName(String username) {
		
		return userMapper.queryUserByName(username);
	}

	public List<UserInfo> queryUserList() {
		return userMapper.queryUserList();
	}

	public Integer addUser(UserInfo user) {
		
		return userMapper.addUser(user);
	}

	//��ѯ�ܼ�¼��
	public int queryMaxCount(String queryTypeId, String queryKeyWords) {
		Map<String,String> mapParams = new HashMap<String,String>();
		mapParams.put("queryTypeId", queryTypeId);
		mapParams.put("queryKeyWords", queryKeyWords);
		return userMapper.queryMaxCount(mapParams);
	}

	//���ݷ�ҳʵ���ѯ�б�
	public List<UserInfo> queryUserList(PageData pageData,String queryTypeId,
			String queryKeyWords) {
		//��ʼ������
		int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
		//ҳ��С
		int pageSize = pageData.getPageSize();
		
		//��װ��Map
		Map<String,Object> mapParams = new HashMap<String,Object>();
		mapParams.put("startIndex", startIndex);
		mapParams.put("pageSize", pageSize);
		mapParams.put("queryTypeId", queryTypeId);
		mapParams.put("queryKeyWords", queryKeyWords);
		
		return userMapper.queryUserList(mapParams);
	}

	public Integer deleteUserById(int userId) {
		//��ɾ���û�����
		
		//��ɾ���û�
		
		return userMapper.deleteUserById(userId);
	}

	public Integer deleteUserBatch(String ids) {
		//"20,21"
		String[] idsArr = ids.split(",");
		return userMapper.deleteUserBatch(idsArr);
	}


}
