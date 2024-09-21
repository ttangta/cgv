package com.example.Cgv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Cgv.DAO.MemberDAO;
import com.example.Cgv.DTO.MemberInfoDTO;
import com.example.Cgv.Entity.Member;

@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	// 로그인 (= 아이디와 비밀번호로 특정 entity 찾기)
	public Member login(String id, String pwd) {
		return memberDAO.login(id, pwd);
	}
	// 회원가입 전 회원 존재 유무 판단
	public boolean checkMember(MemberInfoDTO dto) {
		return memberDAO.checkMember(dto);
	}
}
