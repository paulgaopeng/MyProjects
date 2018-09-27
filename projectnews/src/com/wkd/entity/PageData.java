package com.wkd.entity;

/**
 * 分页实体类
 */
public class PageData {
	private int currPage = 1; // 当前页码 ,默认为1
	private int pageSize = 5; // 页大小 默认为 5
	private int maxCount; // 总记录数
	private int sumPages; // 总页数

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
