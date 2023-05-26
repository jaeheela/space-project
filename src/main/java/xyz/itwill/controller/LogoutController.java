package xyz.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	//로그아웃 처리 후 메인 페이지를 요청하기 위한 URL 주소를 클라이언트에게 전달하는 요청 처리 메소드
	@RequestMapping("/member")
	public String memberLogout(HttpSession session) {
		//session.removeAttribute("loginUserinfo");
		session.invalidate();
		return "redirect:/";
	}
		
	@RequestMapping("/host")
	public String hostLogout(HttpSession session) {
		//session.removeAttribute("loginUserinfo");
		session.invalidate();
		return "redirect:/host";
	}
	
	@RequestMapping("/admin")
	public String adminLogout(HttpSession session) {
		//session.removeAttribute("loginUserinfo");
		session.invalidate();
		return "redirect:/admin";
	}
}
