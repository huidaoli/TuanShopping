package com.xiang.tuanshopping.bean;

public class Page {

	private Integer pages;
	private Integer count;
	private Integer current;
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	@Override
	public String toString() {
		return "Page [pages=" + pages + ", count=" + count + ", current="
				+ current + "]";
	}
	
}
