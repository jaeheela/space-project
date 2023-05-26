package xyz.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Host;
import xyz.itwill.dto.Member;
import xyz.itwill.exception.LoginAuthFailException;
import xyz.itwill.service.HostService;
import xyz.itwill.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
	
	private final MemberService memberService;
	private final HostService hostService;
	
	/**
	 * 게스트 로그인 페이지 이동 - 권한없음 , 예외없음
	 * @return
	 */
	@RequestMapping(value = "/member",method = RequestMethod.GET)
	public String memberLogin() {
		return "member/member_login";
	}
	
	/**
	 * 게스트 로그인 처리 - 권한없음, 전달받은 아이디와 비밀번호에 대한 인증이 실패되면 예외처리, 인증 성공하면 session에 저장
	 * @param member
	 * @param session
	 * @return
	 * @throws LoginAuthFailException
	 */
	@RequestMapping(value = "/member",method = RequestMethod.POST)
	public String memberLogin(@ModelAttribute Member member, HttpSession session) throws LoginAuthFailException {
		Member loginMember = memberService.loginAuth(member);
		session.setAttribute("loginMember", loginMember);
		return "redirect:/";
	}
		
	/**
	 * 호스트 로그인 페이지 이동 - 권한없음 , 예외없음
	 * @return
	 */
	@RequestMapping(value = "/host",method = RequestMethod.GET)
	public String hostLogin() {
		return "host/host_login";
	}

	/**
	 * 호스트 로그인 처리 -  권한없음, 전달받은 아이디와 비밀번호에 대한 인증이 실패되면 예외처리, 인증 성공하면 session에 저장
	 * @param host
	 * @param session
	 * @return
	 * @throws LoginAuthFailException
	 */
	@RequestMapping(value = "/host",method = RequestMethod.POST)
	public String hostLogin(@ModelAttribute Host host, HttpSession session) throws LoginAuthFailException {
		Host loginHost = hostService.loginAuth(host);
		session.setAttribute("loginHost", loginHost);
		
		if(loginHost.getHStatus()==9) {
			return "redirect:/admin";
		}
		return "redirect:/host";
	}	
	
	
	@ExceptionHandler(value = LoginAuthFailException.class)
	public String MemberExceptionHandler(LoginAuthFailException exception, Model model) {
		
	    if (exception.getMessage().contains("호스트")) {
			model.addAttribute("message", exception.getMessage()); 
			model.addAttribute("id", exception.getId()); 
	        return "host/host_login"; 
	    } else {
			model.addAttribute("message", exception.getMessage()); 
			model.addAttribute("id", exception.getId()); 
			return "member/member_login";
	    }
	}
	
	
	

}
