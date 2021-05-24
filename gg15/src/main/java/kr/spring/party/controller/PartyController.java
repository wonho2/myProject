package kr.spring.party.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class PartyController {
	
	//의존 관계 설정
	@Resource
	private PartyService partyService;
	
	//자바빈 초기화
	@ModelAttribute
	public PartyVO initCommand() {
		return new PartyVO();
	}
	
	//===게시글 목록===/
	@RequestMapping("/party/list.do")
	public ModelAndView boardList(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		//페이징 처리
		/*int count = partyService.selectBoardCount();
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		List<PartyVO> selectboardList = null;
		if(count > 0)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			selectboardList = partyService.selectBoardList(map);
		}*/
		//데이터 저장 및 반환
		ModelAndView mav = new ModelAndView();
		mav.setViewName("partyList");
		//mav.addObject("boardList", boardList);
		return mav;
	}
	
	//===게시판 글쓰기===/
	@RequestMapping(value="/party/write.do", method=RequestMethod.GET)
	public String writeFrom()
	{
		   
		return "partyWrite";
	}
	
	@RequestMapping(value="/party/write.do", method=RequestMethod.POST)
	public String writeSubmit(@Valid PartyVO partyVO, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		//유효성 오류가 있는 경우 폼 호출
		if(result.hasErrors()) {
			return writeFrom();
		}
		//회원 번호 
		//Integer mem_num = (Integer)session.getAttribute("mem_num");
		//partyVO.setMem_num(mem_num);
		partyVO.setMem_num(1);
		
		//글쓰기
		partyService.insertBoard(partyVO);
		return "redirect:/party/list.do";
	}
	
	//===게시글 내용보기===//
	//게시물 상세
	/*@RequestMapping("/party/detail.do")
	public ModelAndView boardDetail(@RequestParam int num)
	{
		//해당 글의 조회수 증가
		partyService.updateView(num);
		//html 태그 불허
		PartyVO partyVO = partyService.selectBoard(num);
		partyVO.setTitle(StringUtil.useNoHtml(partyVO.getTitle()));
		partyVO.setContent(StringUtil.useBrNoHtml(partyVO.getContent()));
		return new ModelAndView("PartyDetail","partyVO", partyVO);
	}
	
	//이미지 출력
	@RequestMapping("/party/imageView.do")
	public ModelAndView imageView(@RequestParam int num)
	{
		PartyVO partyVO = partyService.selectBoard(num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("uploadFile", partyVO.getUploadfile());
		return mav;
	}*/
	
	//===게시글 수정===//
	/*@RequestMapping(value="/party/modify.do", method=RequestMethod.GET)
	public String modifyForm(@RequestParam int num, Model model)
	{
		PartyVO partyVO = partyService.selectBoard(num);
		model.addAttribute("partyVO", partyVO);
		return "PartyModify";
	}
	
	@RequestMapping(value="/party/modify.do", method=RequestMethod.POST)
	public String modifySubmit(@Valid PartyVO partyVO, BindingResult result, HttpServletRequest request)
	{
		//유효성 오류가 있는 경우 폼 호출
		if(result.hasErrors()) return "partyModify";
		
		partyService.updateBoard(partyVO);
		return "redirect:/board/list.do";
	}*/
	
	//===게시글 삭제===//
	/*@RequestMapping("/party/delete.do")
	public String deleteSubmit(@RequestParam int num)
	{
		partyService.deleteBoard(num);
		return "redirect:/party/list.do";
	}*/
}











