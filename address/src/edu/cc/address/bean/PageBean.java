package edu.cc.address.bean;

import java.util.List;

/**
 * 封装分页信息
 * @author song
 *
 */
public class PageBean {
	private List<?> rows;
	
	private int page=1;
	private int pageSize=20;
	private int total;
	
	public int getPageCount(){
		int count=total/pageSize;
		if(total%pageSize!=0){
			count+=1;
		}
		return count;
		//return (total+pageSize-1)/pageSize;
	}
	
	public boolean isFirst(){
		return page==1||total==0;
	}
	
	public boolean isLast(){
		return page==getPageCount()||total==0;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
