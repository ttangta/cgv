package com.example.Cgv.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="user_no")
	@SequenceGenerator(name = "user_no", sequenceName ="userNo", allocationSize = 1)
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
	private String logtime;
	
}
