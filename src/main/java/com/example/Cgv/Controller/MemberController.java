package com.example.Cgv.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Cgv.DTO.MemberInfoDTO;
import com.example.Cgv.Entity.Member;
import com.example.Cgv.Service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	// 1) 메인으로 이동
	@GetMapping("/")
	public String main() {
		return "main";
	}
	// 2) 관리자 창으로 이동
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	// 3) 로그인 폼
	@GetMapping("/loginForm")
	public String loginForm(HttpServletRequest request,Model model) {
		int errorCode = 0;
		if(request.getParameter("errorCode") != null && !request.getParameter("errorCode").equals("")) {
			errorCode = Integer.parseInt(request.getParameter("erroCode"));
		}
		
		model.addAttribute("errorCode", errorCode);
		return "member/loginForm";
	}
	// 4) 로그인
	@PostMapping("/login")
	public String login(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		int errorCode = 0;
		
		// 데이터가 넘어오는지 확인
		/*
		System.out.println("로그인 => id : " + id);
		System.out.println("로그인 => pwd : " + pwd);
		*/
		Member user = memberService.login(id, pwd);
		
		//System.out.println("로그인 => member : " + user);
		if(user != null) {
			// 세션 설정
			HttpSession session = request.getSession();
			session.setAttribute("MemId", user.getId());
			return "/main";
		}else {
			errorCode = 1;
			model.addAttribute("errorCode", errorCode);
			// => Member user의 값이 null일 경우 "MemId" : null
			return "/member/loginForm";
		}
	}
	// 5) 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("MemId");
		return "main";
	}
	// 6) 회원가입 폼 => 먼저 회원 정보를 입력받은 후 존재하는 회원일경우 아이디 찾기와 비밀번호 찾기 존재하지 않을 경우 회원가입 창 가기
	@GetMapping("/checkUserForm")
	public String checkUserForm() {
		return "/member/checkUserForm";
	}
	// 7) 6번의 정보로 회원인지 판단
	@PostMapping("/checkUser")
	public String checkUser(HttpServletRequest request,Model model){
		//System.out.println("이름/생년월일/전화번호로 회원인지 판단");
		// => 해당 컨트롤러 반응 ok
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		// test
		//System.out.println("7) 입력받은 정보 : " + name + " " + birthday + " " + tel);
		//  생일/전화번호를 db에 맞는 형태로 구분짖기 ex) 980222
		String byear = "19"  + birthday.substring(0,2);
		String bmonth = birthday.substring(2,4);
		String bday = birthday.substring(4,birthday.length());
		// 다음 작업 전화번호 구분짖기
		String tel1 = tel.substring(0, 3);
		String tel2 = tel.substring(3,7);
		String tel3 = tel.substring(7, tel.length());
		MemberInfoDTO memberInfoDTO = new MemberInfoDTO(name,byear, bmonth, bday, tel1, tel2, tel3);
		//System.out.println(memberInfoDTO);
		boolean result = memberService.checkMember(memberInfoDTO);
		System.out.println(result);
		// 회원이 존재하면 true 존재하지않으면 false 값 전달 받음
		// -> result 결과에 따라 다른 thymeleaf로 이동하게 구현하자(2024-09-21)
		return null;
	}
	
}
