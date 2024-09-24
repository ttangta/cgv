package com.example.Cgv.DAO;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Cgv.DTO.MemberDTO;
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
		String bmonth = dto.getBmonth();
		String bday = dto.getBday();
		String tel1 = dto.getTel1();
		String tel2 = dto.getTel2();
		String tel3 = dto.getTel3();
		Member member = memberRepository.findByNameAndBirthdayAndTel(name,bmonth,bday,tel1,tel2,tel3);
		if(member != null)result = true;
		return result;
	}
	// 가입 전 회원정보 유무 판단 후 존재하는 회원일 경우 member entity 반화
	public Member trueMember(MemberInfoDTO dto) {
		String name = dto.getName();
		String bmonth = dto.getBmonth();
		String bday = dto.getBday();
		String tel1 = dto.getTel1();
		String tel2 = dto.getTel2();
		String tel3 = dto.getTel3();
		return memberRepository.findByNameAndBirthdayAndTel(name, bmonth, bday, tel1, tel2, tel3);
	}
	// 아이디로 회원정보 존재유무 판단하기
	public boolean findById(MemberInfoDTO dto) {
		boolean result = false;
		String id = dto.getId();
		String bmonth = dto.getBmonth();
		String bday = dto.getBday();
		String tel1 = dto.getTel1();
		String tel2 = dto.getTel2();
		String tel3 = dto.getTel3();
		Member member = memberRepository.findByIdAndBirthdayAndTel(id, bmonth, bday, tel1, tel2, tel3);
		if(member != null)result = true;
		return result;
	}
	// 임시 비밀번호로 db 업데이트
	public boolean updatetemporaryPw(MemberInfoDTO dto,String temPw) {
		boolean result = false;
		String id = dto.getId();
		String bmonth = dto.getBmonth();
		String bday = dto.getBday();
		String tel1 = dto.getTel1();
		String tel2 = dto.getTel2();
		String tel3 = dto.getTel3();
		Member member = memberRepository.findByIdAndBirthdayAndTel(id, bmonth, bday, tel1, tel2, tel3);
		
		if(member != null) {
			member.setPwd(temPw);
			member.setLogtime(new Date());
			Member updatePwMember = memberRepository.save(member);
			result = true;
		}
				
		return result;
	}
	// 회원의 이메일 정보 받아오기
	public Member findEmail(String id) {
		return memberRepository.findByIdforEmail(id);
	}
}