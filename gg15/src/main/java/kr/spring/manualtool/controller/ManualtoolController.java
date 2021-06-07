package kr.spring.manualtool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.manualtool.service.ManualtoolService;
import kr.spring.manualtool.vo.ManualtoolVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class ManualtoolController {
	private Logger log = Logger.getLogger(this.getClass());
	
	//의존 관계 설정
	@Resource
	private ManualtoolService manualtoolService;
	
	//자바빈(VO) 초기화
	@ModelAttribute("manualtoolVO")
	public ManualtoolVO initCommand() {
		return new ManualtoolVO();
	}
	//====게시판 글 등록=======//
	//등록 폼
	@RequestMapping(value="/manualTool/write.do", method=RequestMethod.GET)
	public String writeForm() {
		return "manualtoolWrite";
	}
	//전송된 데이터 처리
	@RequestMapping(value="/manualTool/write.do", method=RequestMethod.POST)
	public String writeSubmit(@Valid ManualtoolVO manualtoolVO,
			             BindingResult result,
			             HttpServletRequest request,
			             HttpSession session) {
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "manualtoolWrite";
		}
		
		//회원 번호 셋팅
		manualtoolVO.setMem_num((Integer)session.getAttribute("user_num"));
		//글쓰기
		manualtoolService.insertManualtool(manualtoolVO);
		
		return "redirect:/manualTool/list.do";
	}
	
	//=====게시판 글 목록=====//
	@RequestMapping("/manualTool/list.do")
	public ModelAndView ManualtoolList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			                           @RequestParam(value="keyfield", defaultValue="") String keyfield,
			                           @RequestParam(value="keyword", defaultValue="") String keyword,
			                           @RequestParam(value="keyuser", defaultValue="") String keyuser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("keyuser", keyuser);
		//총 레코드 수
		int count = manualtoolService.selectManualtoolCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<count>> : " + count);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}
		
		//페이징 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage, count, 10, 10, "list.do","&keyuser="+keyuser);
		
		List<ManualtoolVO> manualtoolList = null;
		if(count > 0) {
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			manualtoolList = manualtoolService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manualtoolList");
		mav.addObject("count", count);
		mav.addObject("manualtoolList", manualtoolList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//====게시판 글 상세======//
	@RequestMapping("/manualTool/detail.do")
	public ModelAndView detail(@RequestParam int man_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<man_num>> : " + man_num);
		}
		
		//해당 글의 조회수 증가
		manualtoolService.updateHit(man_num);
		
		ManualtoolVO manualtoolVO = manualtoolService.selectManualtool(man_num);
		//HTML 태그 불허
		manualtoolVO.setMan_title(StringUtil.useNoHtml(manualtoolVO.getMan_title()));
		//HTML 태그 불허 및 줄바꿈 처리
		manualtoolVO.setMan_content(StringUtil.useBrNoHtml(manualtoolVO.getMan_content()));
		
		return new ModelAndView("manualtoolView", "manualtoolVO", manualtoolVO);
		
	}
	//이미지 출력
	@RequestMapping("/manualTool/imageView.do")
	public ModelAndView imageView(@RequestParam int man_num) {
		ManualtoolVO manualtoolVO = manualtoolService.selectManualtool(man_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", manualtoolVO.getMan_uploadfile());
		mav.addObject("filename", manualtoolVO.getMan_filename());
		
		return mav;
	}
	
	//=====게시판 글 수정======//
	//수정 폼
	@RequestMapping(value="/manualTool/update.do", method=RequestMethod.GET)
	public String formUpdate(@RequestParam int man_num, Model model) {
		ManualtoolVO manualtoolVO = manualtoolService.selectManualtool(man_num);
		model.addAttribute("manualtoolVO", manualtoolVO);
		
		return "manualtoolModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/manualTool/update.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid ManualtoolVO manualtoolVO,
			                   BindingResult result,
			                   HttpServletRequest request) {
//		//유효성 체크 결과 오류가 있으면 폼 호출
//		if(result.hasErrors()) {
//			return "manualtoolModify";
//		}
		
		//글 수정
		manualtoolService.updateManualtool(manualtoolVO);
		
		return "redirect:/manualTool/list.do";
	}
	
	//======게시판 글 삭제========//
	@RequestMapping("/manualTool/delete.do")
	public String submitDelete(@RequestParam int man_num) {
		//글 삭제
		manualtoolService.deleteManualtool(man_num);
		
		return "redirect:/manualTool/list.do";
	}
	
}
