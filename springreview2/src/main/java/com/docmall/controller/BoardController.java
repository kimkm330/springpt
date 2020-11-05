package com.docmall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.dto.MemberDTO;
import com.docmall.domain.BoardVO;
import com.docmall.util.PageMaker;
import com.docmall.util.SearchCriteria;
import com.docmall.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service; // BoardServiceImpl클래스의 객체주입이 됨
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeForm() {
		logger.info("write get........");
	}
	
	@RequestMapping(value = "/writeaction", method = RequestMethod.POST)
	public String writeAction(BoardVO vo, HttpSession session) throws Exception {
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		vo.setMem_id(dto.getMem_id());
		//vo.setBD_CONTENT("gfuyh");
		logger.info(vo.toString());
		
		service.create(vo);
		
		return "redirect:/board/listPage";
	}
	
	//페이징쿼리를 사용한 목록데이타
	// SearchCriteria cri : 다음 파라미터 클래스는 스프링에서 자동으로 객체생성을 해준다.
	// 처음 요청시 page=1, perPageNum=2, rowStart=0, rowEnd=0, searchType=null, keyword=null
	// 검색 요청시 page=1, perPageNum=2, rowStart=0, rowEnd=0, searchType=w, keyword=냉무
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		//cri 객체 변수의 값
		//page = 1; // 현재 페이지 번호.
		//perPageNum = 5; // 출력 게시물 개수
		logger.info(cri.toString());
		
		//페이징쿼리를 통한 게시판 데이타정보들
		model.addAttribute("listPage", service.listSearch(cri));
		
		// totalCount=0, startPage=0, endPage=0, prev=false, next=false, 
		// displayPageNum=5, Criteria cri = null
		PageMaker pageMaker = new PageMaker();
		
		
		pageMaker.setCri(cri);  //   page=1, perPageNum=2
		
		// 검색기능 까지 포함한 테이블의 전체 레코드 수
		pageMaker.setTotalCount(service.listSearchCount(cri));
		//페이징정보를 통하여 페이징작업
		
		// pageMaker 객체의 정보 : 
		// totalCount=전체 데이타수(검색포함), startPage=0, endPage=0, prev=false, next=false, 
		// displayPageNum=5, Criteria cri ( page=1, perPageNum=2)
		
		model.addAttribute("pageMaker", pageMaker);
		
		
		// "listPage.jsp" 에서  model 이 2개 사용(필드(변수)가 사용되는 것이 아니라, getter 메서드 사용). 
		// model 첫번째 키이름 : "listPage" - 페이징쿼리에 의한 게시물 정보(선택적 :검색포함)
		// model 두번째 키이름  : "pageMaker" - 페이징 정보(선택적 :검색포함)
		
	}
	
	//게시물 보기(뷰)
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info("read...");
		
		service.viewIncrement(bno);
		
		model.addAttribute("boardVO", service.read(bno));
		
	}
	
	//readPage.jsp에서 수정하기 클릭 후 호출됨.
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyGet(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info("modifyGet....");
		model.addAttribute("boardVO", service.read(bno));
	}
	
	//수정페이지에서 수정하기 진행
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPost(BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("modifyPage post....");
		service.update(vo);
		
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		logger.info(rttr.toString());
		
		//return "redirect:/board/listAll";
		// /board/listPage?page=1&perPageNum=2&searchType=w&keyword=
		return "redirect:/board/listPage";
	}
	
	//삭제하기 버튼을 클릭하면 호출
	@RequestMapping(value = "/removePage", method = RequestMethod.GET)
	public String remove(int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("removePage get....");
		
		service.delete(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		logger.info(rttr.toString());
		return "redirect:/board/listPage";
		
	}
	
	
}
