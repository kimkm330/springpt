package com.docmall.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.dto.MemberDTO;
import com.docmall.domain.MemberVO;
import com.docmall.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwdEncrypt;
	
	// 로그인(GET)
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void loginGET() {
		
	}
	
	// 로그인(POST)
	@RequestMapping(value="loginPost", method=RequestMethod.POST)
	public String loginPOST(MemberDTO dto, RedirectAttributes rttr, HttpSession session, 
							Model model, HttpServletResponse response) throws Exception {
		
		logger.info("=====loginPost() execute...");
	
	    // DB에서 암호화된 비밀번호가 저장
		MemberDTO memDTO = service.login(dto);
		
		if(memDTO != null) { // 로그인 성공
			logger.info("=====로그인 성공");
			
			// 세션 작업 
			session.setAttribute("user", memDTO); 
			
			// 쿠키를 사용할 경우. 저장소 : 클라이언트(브라우저).
			if(memDTO.isUseCookie()) {
				
				// 쿠키 저장
				int amount= 60*60*24*7;  //  7일

				//  System.currentTimeMillis() : 서버의 현재시간을 밀리세컨드 읽어온다.
				Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));

				// 쿠키작업이 완료되지 않음.  현재는 DB와 연동작업으로 되어있음.
				service.saveCookie(session.getId(), sessionLimit, memDTO.getMem_id());
			}
			
			
			rttr.addFlashAttribute("msg", "LOGIN_SUCCESS");
			return "redirect:/"; 
			
		} else {		 // 로그인 실패
			logger.info("=====로그인 실패");
			

			rttr.addFlashAttribute("msg", "LOGIN_FAIL"); //로그인 페이지에서 "msg" 키가 사용됨.
			
			return "redirect:/member/login"; 
		}
		
	}
	
	// 로그아웃  
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		logger.info("=====logout execute()...");
		
		session.invalidate(); // 세션으로 처리한 정보가 서버메모리에서 제거-> 로그아웃기능
		rttr.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		
		return "redirect:/";
	}
	
	
	// 회원가입(GET) 
	@RequestMapping(value="join", method=RequestMethod.GET)
	public void joinGET() {
	}
	
	// 회원가입(POST)
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String joinPOST(MemberVO vo, RedirectAttributes rttr) throws Exception {
		
		// 비밀번호 암호화
		vo.setMem_pw(passwdEncrypt.encode(vo.getMem_pw()));
		
		service.join(vo);
		rttr.addFlashAttribute("msg", "REGISTER_SUCCESS");
		
		return "redirect:/";
	}
	
	
	// 아이디 중복체크(ajax요청)
	@ResponseBody
	@RequestMapping(value = "checkIdDuplicate", method=RequestMethod.POST)
	public ResponseEntity<String> checkIdDuplicate(@RequestParam("mem_id") String mem_id) throws Exception {
		
		logger.info("=====checkIdDuplicate execute()...");
		ResponseEntity<String> entity = null;
		try {
			int count = service.checkIdDuplicate(mem_id);
			// count 가 0이면 아이디 사용가능, 1d 이면 사용 불가능.

			if(count != 0) {
				// 아이디가 존재해서 사용이 불가능.
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			} else {
				// 사용가능한 아이디
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	

	
	// 이메일 인증 코드 확인 
	@ResponseBody
	@RequestMapping(value = "checkAuthcode", method=RequestMethod.POST)
	public ResponseEntity<String> checkAuthcode(@RequestParam("code") String code, HttpSession session){
		
		ResponseEntity<String> entity = null;
		
		try {
			if(code.equals(session.getAttribute("authcode"))) {
				// 인증코드 일치
				entity= new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				// 인증코드 불일치
				entity= new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 비밀번호 재확인(GET)
	/* 1)회원정보 수정 url=modify,  2)비밀번호 변경 url=changePw, 3)회원 탈퇴  url=delete */
	@RequestMapping(value="checkPw", method=RequestMethod.GET)
	public void checkPwGET(@ModelAttribute("url") String url) {
		
	}
	
	// 비밀번호 재확인(POST)
	// 1)회원정보 수정 url=modify,  2)비밀번호 변경 url=changePw, 3)회원 탈퇴  url=delete 
	@RequestMapping(value="checkPw", method=RequestMethod.POST)
	public String checkPwPOST(@RequestParam("url") String url,
							  @RequestParam("mem_pw") String pw,
							  HttpSession session, Model model) throws Exception {
		
		logger.info("=====checkPw() execute..."); 
		logger.info("=====url: " + url + ", mem_pw: " + pw); 
		// 인증된 사용자
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		// 스프링 시큐리티(보안)
		if(passwdEncrypt.matches(pw, dto.getMem_pw())) {
			// 비밀번호가 일치하는 경우, url 확인 
			if(url.equals("modify")) {
				model.addAttribute("vo", service.readUserInfo(dto.getMem_id()));
				return "/member/modify"; // 회원정보 수정 뷰
				
			} else if(url.equals("changePw")) {
				return "/member/changePw"; // 비밀번호변경 뷰
				
			} else if(url.equals("delete")) {
				return "/member/delete";  // 회원탈퇴 뷰
				
			}
		} 
		
		// 비밀번호가 일치하지 않거나, url이 정해진 url이 아닌 경우
		model.addAttribute("url", url);
		model.addAttribute("msg", "CHECK_PW_FAIL");
		return "/member/checkPw";
	}
	
	// 비밀번호 확인 Ajax용
	@ResponseBody
	@RequestMapping("checkPwAjax")
	public ResponseEntity<String> checkPwAjax(@RequestParam("mem_pw") String mem_pw, HttpSession session) {
		
		logger.info("=====checkPwAjax() execute...");
		ResponseEntity<String> entity = null;
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		logger.info("=====mem_pw: " + mem_pw);
		logger.info("=====dto: " + dto.toString());
		
		if(passwdEncrypt.matches(mem_pw, dto.getMem_pw())) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		}
		
		return entity;
	}
	
	
	// 회원 정보 수정(POST)
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPOST(MemberVO vo, RedirectAttributes rttr, HttpSession session) throws Exception {

		logger.info("=====modifyPOST() execute...");
		
		MemberDTO dto = new MemberDTO();
		dto.setMem_id(vo.getMem_id());
		
		service.modifyUserInfo(vo); // 회원수정
		

		// 세션작업. 수정중에서 변경된 정보를 갱신.
		session.setAttribute("user", dto);
		
		rttr.addFlashAttribute("msg", "MODIFY_USER_SUCCESS");
		
		return "redirect:/";
	}

	
	
	// 비밀번호 변경(POST) 
	@RequestMapping(value="changePw", method=RequestMethod.POST)
	public String changePWPOST(MemberDTO dto, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		logger.info("=====changePWPOST() execute...");
		// 비밀번호 암호화 후 변경
		dto.setMem_pw(passwdEncrypt.encode(dto.getMem_pw()));
		service.changePw(dto);
		
		// 세션의 비밀번호 재설정
		MemberDTO memDTO = (MemberDTO) session.getAttribute("user");
		memDTO.setMem_pw(dto.getMem_pw());
		session.setAttribute("user", memDTO);
		
		rttr.addFlashAttribute("msg", "CHANGE_PW_SUCCESS");
		return "redirect:/";
	}
	
	
	// 회원 탈퇴(POST) 
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String deletePOST(String mem_id, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		logger.info("=====deletePOST() execute...");
		
		service.deleteUser(mem_id);
		// 회원탈퇴시 세션 소멸작업
		session.invalidate();
		rttr.addFlashAttribute("msg", "DELETE_USER_SUCCESS");
		
		return "redirect:/";
	}
	
}
