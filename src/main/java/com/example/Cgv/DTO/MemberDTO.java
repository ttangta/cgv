package com.example.Cgv.DTO;

import java.sql.Date;

import com.example.Cgv.Entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private int seq;
	private String name;
	private String id;
	private String pwd;
	private String tel1;
	private String tel2;
	private String tel3;
	private String email1;
	private String email2;
	private String byear;
	private String bmonth;
	private String bday;
	private Date logtime;
	
	// Entity로 변환하는 메소드
	public Member toEntity() {
		return new Member(seq, name, id, pwd, tel1, tel2, tel3, email1, email2, byear, bmonth, bday, bday);
	}
}
