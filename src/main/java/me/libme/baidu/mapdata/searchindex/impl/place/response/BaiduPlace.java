package me.libme.baidu.mapdata.searchindex.impl.place.response;

public class BaiduPlace {
	private String uid;//poi的唯一标示
	
	private String name;//poi名称
	
	private BaiduLocation location;//经纬度
	
	private String address;
	
	private String province;//省
	
	private String city;//市
	
	private String area;//区
	
	private String street_id;//街景图id
	
	private String detail;//是否有详情页：1有，0没有
	
	private String tag;//类型

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaiduLocation getLocation() {
		return location;
	}

	public void setLocation(BaiduLocation location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
