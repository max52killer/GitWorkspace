package edu.cc.sshe.framework.bean;

import java.util.List;

/**
 * 对应了Easyui中DataGrid所要求的数据格式
 * @author song
 *
 */
public class GridData {
	private int total;
	private List<?> rows;
	public GridData() {
		super();
	}
	public GridData(int total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "GridData [total=" + total + ", rows=" + rows + "]";
	}
}
