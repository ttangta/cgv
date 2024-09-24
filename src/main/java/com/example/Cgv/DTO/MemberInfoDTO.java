package com.example.Cgv.DTO;

import java.beans.JavaBean;

@JavaBean
public class MemberInfoDTO {
	private String name;
	private String id;
	private String byear;
	private String bmonth;
	private String bday;
	private String tel1;
	private String tel2;
	private String tel3;
	
	public MemberInfoDTO() {};
	
	public MemberInfoDTO(String name,String id,String byear, String bmonth, String bday, String tel1, String tel2, String tel3) {
		this.name = name;
		this.id = id;
		this.byear = byear;
		this.bmonth = bmonth;
		this.bday = bday;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;	
	}
	
	public MemberInfoDTO(String name,String byear,String bmonth, String bday, String tel1, String tel2, String tel3) {
		this.name = name;
		this.byear = byear;
		this.bmonth = bmonth;
		this.bday = bday;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
	}
	
	public MemberInfoDTO(String id,String bmonth, String bday, String tel1, String tel2, String tel3) {
		this.id = id;
		this.bmonth = bmonth;
		this.bday = bday;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
	}

	@Override
	public String toString() {
		return "MemberInfoDTO [name=" + name + ", id=" + id + ", byear=" + byear + ", bmonth=" + bmonth + ", bday="
				+ bday + ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getByear() {
		return byear;
	}

	public void setByear(String byear) {
		this.byear = byear;
	}

	public String getBmonth() {
		return bmonth;
	}

	public void setBmonth(String bmonth) {
		this.bmonth = bmonth;
	}

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	
	
	
}
