package com.checklod.web.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@Controller
public class LoginController {
	// private MemberService memberService;

	// 메인 페이지
	@GetMapping("/")
	public String index() {
		return "/index";
	}

//    // 회원가입 페이지
//    @GetMapping("/user/signup")
//    public String dispSignup() {
//        return "/signup";
//    }

//    // 회원가입 처리
//    @PostMapping("/user/signup")
//    public String execSignup(MemberDto memberDto) {
//        memberService.joinUser(memberDto);
//
//        return "redirect:/user/login";
//    }

	// 로그인 페이지
	@GetMapping("/login")
	public String dispLogin(@AuthenticationPrincipal User user) {
		if (user != null) {
			if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VIEW"))) {
				return "/index";
			}
		}
		return "/login";
	}

	// 로그인 결과 페이지
//	@GetMapping("/user/login/result")
//	public String dispLoginResult() {
//		return "/loginSuccess";
//	}

//    // 로그아웃 결과 페이지
//    @GetMapping("/user/logout/result")
//    public String dispLogout() {
//        return "/logout";
//    }

//    // 접근 거부 페이지
//    @GetMapping("/user/denied")
//    public String dispDenied() {
//        return "/denied";
//    }

//    // 내 정보 페이지
//    @GetMapping("/user/info")
//    public String dispMyInfo() {
//        return "/myinfo";
//    }

//    // 어드민 페이지
//    @GetMapping("/admin")
//    public String dispAdmin() {
//        return "/admin";
//    }
}
