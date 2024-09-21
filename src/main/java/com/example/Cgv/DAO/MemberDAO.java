package com.example.Cgv.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Cgv.DTO.MemberInfoDTO;
import com.example.Cgv.Entity.Member;
import com.example.Cgv.Repository.MemberRepository;

@Repository
public class MemberDAO {
	@Autowired
	MemberRepository memberRepository;
	
	// 로그인 (= 아이디와 비밀번호로 특정 entity 찾기)
	public Member login(String id, String pwd) {
		return memberRepository.findByIdAndPwd(id,pwd);
	}
	// 회원가입 전 회원 존재 유무 판단
	public boolean checkMember(MemberInfoDTO dto) {
		// 회원정보가 없다면 회원가입
		boolean result = false;
		String name = dto.getName();
		String byear = dto.getByear();
		String bmonth = dto.getBmonth();
		String bday = dto.getBday();
		String tel1 = dto.getTel1();
		String tel2 = dto.getTel2();
		String tel3 = dto.getTel3();
		Member member = memberRepository.findByNameAndBirthdayBirthdayAndTel(name,byear,bmonth,bday,tel1,tel2,tel3);
		if(member != null)result = true;
		return result;
	}
}
