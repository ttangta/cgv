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
	// 가입 전 회원정보 유무 판단 후 존재하는 회원일 경우 member entity 반화
	public Member trueMember(MemberInfoDTO dto) {
		return memberDAO.trueMember(dto);
	}
	// 아이디로 회원정보 존재유무 판단하기
	public boolean findById(MemberInfoDTO dto) {
		return memberDAO.findById(dto);
	}
	// 임시 비밀번호로 db 업데이트
	public boolean updatetemporaryPw(MemberInfoDTO dto,String temPw) {
		return memberDAO.updatetemporaryPw(dto, temPw);
	}
	// 회원의 이메일 정보 받아오기
	public Member findEmail(String id) {
		return memberDAO.findEmail(id);
	}
}
