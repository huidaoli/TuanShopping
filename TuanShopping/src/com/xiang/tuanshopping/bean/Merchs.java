package com.xiang.tuanshopping.bean;

public class Merchs {

	private Integer merchsid;
	private String loc;
	private String image;
	private String range;
	private Integer category;
	private String shorttitle;
	private String describe;
	private String price;
	private String value;
	private String bought;
	private String grade;
	private String gradecount;
	private String shoplevel;
	private String ischooseseat;
	private String shopname;
	private String isholiday;
	private String hosttype;
	private String issubscribe;
	private String date;
	private String city;
	public Integer getMerchsid() {
		return merchsid;
	}
	public void setMerchsid(Integer merchsid) {
		this.merchsid = merchsid;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getShorttitle() {
		return shorttitle;
	}
	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getBought() {
		return bought;
	}
	public void setBought(String bought) {
		this.bought = bought;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGradecount() {
		return gradecount;
	}
	public void setGradecount(String gradecount) {
		this.gradecount = gradecount;
	}
	public String getShoplevel() {
		return shoplevel;
	}
	public void setShoplevel(String shoplevel) {
		this.shoplevel = shoplevel;
	}
	public String getIschooseseat() {
		return ischooseseat;
	}
	public void setIschooseseat(String ischooseseat) {
		this.ischooseseat = ischooseseat;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getIsholiday() {
		return isholiday;
	}
	public void setIsholiday(String isholiday) {
		this.isholiday = isholiday;
	}
	public String getHosttype() {
		return hosttype;
	}
	public void setHosttype(String hosttype) {
		this.hosttype = hosttype;
	}
	public String getIssubscribe() {
		return issubscribe;
	}
	public void setIssubscribe(String issubscribe) {
		this.issubscribe = issubscribe;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Merchs [merchsid=" + merchsid + ", loc=" + loc + ", image="
				+ image + ", range=" + range + ", category=" + category
				+ ", shorttitle=" + shorttitle + ", describe=" + describe
				+ ", price=" + price + ", value=" + value + ", bought="
				+ bought + ", grade=" + grade + ", gradecount=" + gradecount
				+ ", shoplevel=" + shoplevel + ", ischooseseat=" + ischooseseat
				+ ", shopname=" + shopname + ", isholiday=" + isholiday
				+ ", hosttype=" + hosttype + ", issubscribe=" + issubscribe
				+ ", date=" + date + ", city=" + city + "]";
	}
	public Merchs(Integer merchsid, String loc, String image, String range,
			Integer category, String shorttitle, String describe, String price,
			String value, String bought, String grade, String gradecount,
			String shoplevel, String ischooseseat, String shopname,
			String isholiday, String hosttype, String issubscribe, String date,
			String city) {
		super();
		this.merchsid = merchsid;
		this.loc = loc;
		this.image = image;
		this.range = range;
		this.category = category;
		this.shorttitle = shorttitle;
		this.describe = describe;
		this.price = price;
		this.value = value;
		this.bought = bought;
		this.grade = grade;
		this.gradecount = gradecount;
		this.shoplevel = shoplevel;
		this.ischooseseat = ischooseseat;
		this.shopname = shopname;
		this.isholiday = isholiday;
		this.hosttype = hosttype;
		this.issubscribe = issubscribe;
		this.date = date;
		this.city = city;
	}
	public Merchs(Integer merchsid, String loc, String image, String range,
			Integer category, String shorttitle, String describe, String price,
			String value, String bought, String grade, String gradecount,
			String date, String city) {
		super();
		this.merchsid = merchsid;
		this.loc = loc;
		this.image = image;
		this.range = range;
		this.category = category;
		this.shorttitle = shorttitle;
		this.describe = describe;
		this.price = price;
		this.value = value;
		this.bought = bought;
		this.grade = grade;
		this.gradecount = gradecount;
		this.date = date;
		this.city = city;
	}
	
	
	
	
	
	
	
}
