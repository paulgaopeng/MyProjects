package com.wkd.entity;

/**
 * ��ҳʵ����
 */
public class PageData {
	private int currPage = 1; // ��ǰҳ�� ,Ĭ��Ϊ1
	private int pageSize = 5; // ҳ��С Ĭ��Ϊ 5
	private int maxCount; // �ܼ�¼��
	private int sumPages; // ��ҳ��

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getSumPages() {
		sumPages = maxCount % pageSize==0 ? maxCount/pageSize :  (maxCount/pageSize+1) ;
		return sumPages;
	}

}
