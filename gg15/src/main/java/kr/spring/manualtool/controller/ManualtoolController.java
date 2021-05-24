package kr.spring.manualtool.controller;

import java.io.InputStream;
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
	@RequestMapping(value="/manualtool/write.do",method=RequestMethod.GET)
	public String writeForm() {
		return "manualtoolWrite";
	}
	//전송된 데이터 처리
	@RequestMapping(value="/manualtool/write.do",method=RequestMethod.POST)
	public String writeSubmit(@Valid ManualtoolVO manualtoolVO,
			             BindingResult result,
			             HttpServletRequest request,
			             HttpSession session) {
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "writeForm";
		}
		
		//회원 번호 셋팅
		manualtoolVO.setMem_num((Integer)session.getAttribute("mem_num"));
		//글쓰기
		manualtoolService.insertBoard(manualtoolVO);
		
		return "redirect:/manualtool/list.do";
	}
	
	//=====게시판 글 목록=====//
	@RequestMapping("/manualtool/list.do")
	public ModelAndView process(
	@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		
		//총 레코드 수
		int count = manualtoolService.selectBoardCount();
		
		if(log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<count>> : " + count);
		}
		
		//페이징 처리
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		
		List<ManualtoolVO> manualtoolList = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			manualtoolList = manualtoolService.selectBoardList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manualtoolList");
		mav.addObject("count", count);
		mav.addObject("list", manualtoolList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//====게시판 글 상세======//
	@RequestMapping("/manualtool/detail.do")
	public ModelAndView detail(@RequestParam int manualtool_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<manualtool_num>> : " + manualtool_num);
		}
		
		//해당 글의 조회수 증가
		manualtoolService.updateHit(manualtool_num);
		
		ManualtoolVO manualtoolVO = manualtoolService.selectBoard(manualtool_num);
		//HTML 태그 불허
		manualtoolVO.setMan_title(StringUtil.useNoHtml(manualtoolVO.getMan_title()));
		//HTML 태그 불허 및 줄바꿈 처리
		manualtoolVO.setMan_content(StringUtil.useBrNoHtml(manualtoolVO.getMan_content()));
		
		return new ModelAndView("manualtoolView", "manualtool", manualtoolVO);
		
	}
	//이미지 출력
	@RequestMapping("/board/imageView.do")
	public ModelAndView viewImage(@RequestParam int manualtool_num) {
		ManualtoolVO manualtoolVO = manualtoolService.selectBoard(manualtool_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", manualtoolVO.getMan_uploadfile());
		
		return mav;
	}
	
	//=====게시판 글 수정======//
	//수정 폼
	@RequestMapping(value="/manualtool/update.do",method=RequestMethod.GET)
	public String formUpdate(@RequestParam int manualtool_num, Model model) {
		ManualtoolVO manualtoolVO = manualtoolService.selectBoard(manualtool_num);
		model.addAttribute("manualtoolVO", manualtoolVO);
		
		return "manualtoolModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/manualtool/update.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid ManualtoolVO manualtoolVO,
			                   BindingResult result,
			                   HttpServletRequest request) {
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "manualtoolModify";
		}
		
		//글 수정
		manualtoolService.updateBoard(manualtoolVO);
		
		return "redirect:/manualtool/list.do";
	}
	
	//======게시판 글 삭제========//
	@RequestMapping("/manualtool/delete.do")
	public String submitDelete(@RequestParam int manualtool_num) {
		//글 삭제
		manualtoolService.deleteBoard(manualtool_num);
		
		return "redirect:/manualtool/list.do";
	}
	
}




