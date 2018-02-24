package edu.cc.sshe.framework.bean;

import java.util.List;

/**
 * 封装分页信息
 * @author Administrator
 *
 */
public class PageBean {

	private List<?> rows;  //当前页的数据
	private int page = 1;  //当前页
	private int pageSize = 10; //每页多少条数据
	private int total;   //总记录数
	//private int pageCount; //总页数
	
	/**
	 * 计算总页数
	 * @return
	 */
	public int getPageCount() {
		int count = total / pageSize;
		
		if(total % pageSize != 0) {
			count += 1;
		}
		
		return count;
		
		//return (total + pageSize -1 ) / pageSize
	}

   /**
    * 是否为第一页
    * @return
    */
	public boolean isFirst() {
		
		return page == 1 || total == 0;
		
	}
	
	/**
	 * 是否为最后一页
	 * @return
	 */
	public boolean isLast() {
		return page == getPageCount() || total == 0;
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
		if(page < 1) {
			page = 1;
		}
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
