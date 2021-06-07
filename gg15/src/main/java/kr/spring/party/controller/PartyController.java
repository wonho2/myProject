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
	public ModelAndView boardList( 
	@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		//페이징 처리
		int count = partyService.selectPartyCount();
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"list.do");
		List<PartyVO> partyList = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			partyList = partyService.selectPartyList(map);
		}
		//데이터 저장 및 반환
		ModelAndView mav = new ModelAndView();
		mav.setViewName("partyList");
		mav.addObject("count", count);
		mav.addObject("partyList", partyList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//===게시판 글쓰기===/
	@RequestMapping(value="/party/write.do", method=RequestMethod.GET)
	public String writeForm()
	{
		   
		return "partyWrite";
	}
	
	@RequestMapping(value="/party/write.do", method=RequestMethod.POST)
	public String writeSubmit(@Valid PartyVO partyVO, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		//유효성 오류가 있는 경우 폼 호출
		if(result.hasErrors()) {
			return writeForm();
		}
		//회원 번호 
		Integer user_num = (Integer)session.getAttribute("user_num");
		partyVO.setMem_num(user_num);
		
		//글쓰기
		partyService.insertParty(partyVO);
		return "redirect:/party/list.do";
	}
	
	//===게시글 내용보기===//
	//게시물 상세
	@RequestMapping("/party/detail.do")
	public ModelAndView boardDetail(@RequestParam int par_num)
	{
		//해당 글의 조회수 증가
		partyService.updateHit(par_num);
		
		PartyVO partyVO = partyService.selectParty(par_num);
		////HTML 태그 불허
		partyVO.setPar_title(StringUtil.useNoHtml(partyVO.getPar_title()));
		//HTML 태그 불허 및 줄바꿈 처리
		partyVO.setPar_content(StringUtil.useBrNoHtml(partyVO.getPar_content()));
		
		return new ModelAndView("partyDetail","partyVO", partyVO);
	}
	
	//이미지 출력
	@RequestMapping("/party/imageView.do")
	public ModelAndView imageView(@RequestParam int par_num)
	{
		PartyVO partyVO = partyService.selectParty(par_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", partyVO.getPar_uploadfile());
		mav.addObject("filename", partyVO.getPar_filename());
		return mav;
	}
	
	//===게시글 수정===//
	@RequestMapping(value="/party/update.do", method=RequestMethod.GET)
	public String modifyForm(@RequestParam int par_num, Model model)
	{
		PartyVO partyVO = partyService.selectParty
				(par_num);
		model.addAttribute("partyVO", partyVO);
		return "partyModify";
	}
	//===수정 폼에서 데이터 처리===//
	@RequestMapping(value="/party/update.do", method=RequestMethod.POST)
	public String modifySubmit(@Valid PartyVO partyVO, BindingResult result, HttpServletRequest request)
	{
		//유효성 오류가 있는 경우 폼 호출
		if(result.hasErrors()) {
		    return "partyModify";
		}
		
		//글 수정
		partyService.updateParty(partyVO);
		
		return "redirect:/party/list.do";
	}
	
	//===게시글 삭제===//
	@RequestMapping("/party/delete.do")
	public String deleteSubmit(@RequestParam int par_num)
	{
		partyService.deleteParty(par_num);
		
		return "redirect:/party/list.do";
	}

}











