package com.xiang.tuanshopping.bean;

public class UserBean {
	private Integer icon;
	private String name;
	private Integer count;
	
	
	public Integer getIcon() {
		return icon;
	}
	public void setIcon(Integer icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public UserBean(Integer icon, String name,Integer count) {
		super();
		this.icon = icon;
		this.name = name;
		this.count = count;
	}
	
	public UserBean(Integer icon, String name) {
		super();
		this.icon = icon;
		this.name = name;
	}
	public UserBean() {
		super();
	
	}
	@Override
	public String toString() {
		return "UserBean [icon=" + icon + ", name=" + name + ", count=" + count
				+ "]";
	}
	
	
}
