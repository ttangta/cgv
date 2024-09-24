package com.example.Cgv.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Cgv.DTO.MemberInfoDTO;
import com.example.Cgv.Entity.Member;
import com.example.Cgv.Model.TemporaryPw;
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
		String byear = birthday.substring(0,2);
		String bmonth = birthday.substring(2,4);
		String bday = birthday.substring(4,birthday.length());
		// 다음 작업 전화번호 구분짖기
		String tel1 = tel.substring(0, 3);
		String tel2 = tel.substring(3,7);
		String tel3 = tel.substring(7, tel.length());
		MemberInfoDTO memberInfoDTO = new MemberInfoDTO(name,byear, bmonth, bday, tel1, tel2, tel3);
		//System.out.println(memberInfoDTO);
		boolean result = memberService.checkMember(memberInfoDTO);
		//System.out.println(result);
		// 회원이 존재하면 true 존재하지않으면 false 값 전달 받음
		// -> result 결과에 따라 다른 thymeleaf로 이동하게 구현하자(2024-09-21)
		if(result) {
			// 처음 의도와 달라서 코드가 좀 더 복잡해진 것 같다
			// => 처음 의도 result가 true 이면 가입된 정보가 있다라고 판단 => 아이디 찾기 진행
			// => 현재 result가 true이면 가입된 아이디를 바로 보여주기
			Member member = memberService.trueMember(memberInfoDTO);
			// 이름과 아이디 부분을 *로 변환
			String re_name = member.getName();
			String re_id = member.getId();
			int centerName = re_name.length()/2; // 이름이 3자리일 경우 3/2 => 1 4자리일 경우 4/2 -> 2
			int endId = re_id.length();
			re_name = re_name.substring(0,centerName) + "*" + re_name.substring(centerName + 1);
			re_id = re_id.substring(0, endId - 2) + "**";
			model.addAttribute("name", re_name);
			model.addAttribute("id",re_id);
			return"/member/alreadyMember";
		}
		else return "/member/notMember";
	}
	// 8) 아이디& 비밀번호 찾기 폼
	@GetMapping("/findMemberForm")
	public String findMember(HttpServletRequest request, Model model) {
		String menu = request.getParameter("menu");
		// test 용
		if(menu == null || menu.equals(""))menu = "id";
		model.addAttribute("menu",menu);
		return "/member/findMemberForm";
	}
	// 9) 임시비밀번호 받기
	@PostMapping("/temporaryPw")
	public String temporaryPw(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		//System.out.println(id);
		//System.out.println(birthday);
		//System.out.println(tel);
		String bmonth = birthday.substring(2, 4);
		String bday = birthday.substring(4, birthday.length());
		String tel1 = tel.substring(0, 3);
		String tel2 = tel.substring(3, 7);
		String tel3 = tel.substring(7,tel.length());
		MemberInfoDTO dto = new MemberInfoDTO(id, bmonth, bday, tel1, tel2, tel3);
		boolean result = memberService.findById(dto);
		if(result) {
			TemporaryPw temporaryPw = new TemporaryPw();
			//System.out.println("9) => 랜덤한 비밀번호 : " + temporaryPw.randomNumber());
			String temPw = temporaryPw.randomNumber();
			//System.out.println(temPw);
			// 랜덤한 비밀번호는 받았고 db에 임시 비밀번호로 업데이트
			boolean updatePwresult = memberService.updatetemporaryPw(dto, temPw);
			//System.out.println("9) 비밀번호 업데이트 성공여부 => " + updatePwresult);
			if(updatePwresult) {
				model.addAttribute("temPw", temPw);
				Member member = memberService.findEmail(id);
				String email1 = member.getEmail1();
				if(email1.length() > 2) {
														//repeat => 특정 문자를 매개변수 값 만큼 반복
					email1 = email1.substring(0, 2) + "*".repeat(email1.length() - 2);
				}
				String email2 = member.getEmail2();
				String email = email1 + "@" + email2;
				model.addAttribute("email",email);
			}
			return "/member/temporaryPw";
		}

		return null;
	}
	// => 임시비밀번호 받기에서 입력한 정보를 가지고와서 해당 레코드를 찾아야한다.
	//10) 임시비밀번호 전송을 받을 이메일 정보 컨트롤러(ver : 사용자가 이메일을 직접 입력한 경우)
	@PostMapping("/checkEmail")
	public String checkEmail(HttpServletRequest request,@RequestParam("email2") String selectEmail2, Model model) {
		System.out.println("10)번 컨트롤 테스트");
		String email1 = request.getParameter("email1");
		String email2 = selectEmail2;
		if(email2.equals("직접입력")) {
			email2 = request.getParameter("selfWrite");
		}
		//System.out.println(email1);
		//System.out.println(email2);
		String email = email1 + "@" + email2;
		System.out.println(email);
		return null;
	}
	// 11) 임시비밀번호 전송을 받을 이메일 정보 컨트롤러(ver : 기존 사용자의 이메일)
	@GetMapping("/checkEmail")
	public String checkEmail() {
		System.out.println("11) 컨트롤러 진입 성공");
		return null;
	}
}
