package com.riguz.csvonline.bean;

import java.util.List;

public class EasyUiGrid<T> {
	private long total;
	List<T> rows;
	public long getTotal() {
		return this.total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return this.rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
