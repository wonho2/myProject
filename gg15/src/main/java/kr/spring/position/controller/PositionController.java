package kr.spring.position.controller;

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

import kr.spring.position.service.PositionService;
import kr.spring.position.vo.PositionVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class PositionController
{
	@Resource
	private PositionService positionService;
	private Logger log = Logger.getLogger(this.getClass());
	
/*
 * 자바빈 초기화
 */
	@ModelAttribute("positionVO")
	public PositionVO initCommand()
	{
		return new PositionVO();
	}

/*
 * 게시물 목록 (완성)
 */
	@RequestMapping("/position/list.do")
	public ModelAndView boardList(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		// 페이징 처리
		int count = positionService.selectBoardCount();
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		List<PositionVO> boardList = null;
		if(count > 0)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			boardList = positionService.selectBoardList(map);
		}
		// 데이터 저장 및 리턴
		ModelAndView mav = new ModelAndView();
		mav.setViewName("position_list");
		mav.addObject("count", count);
		mav.addObject("boardList", boardList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		return mav;  
	}
	
/*
 * 게시판 글쓰기 (완성)
 */
	@RequestMapping(value="/position/write.do", method=RequestMethod.GET)
	public String writeForm()
	{
		return "position_write";
	}
	
	@RequestMapping(value="/position/write.do", method=RequestMethod.POST)
	public String writeSubmit(@Valid PositionVO positionVO, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		// 유효성 오류가 있는 경우
		if(result.hasErrors()) return "position_write";
		// 정보 셋팅
		Integer mem_num = (Integer)session.getAttribute("user_num");
		positionVO.setMem_num(mem_num);
		// 글쓰기
		positionService.insertBoard(positionVO);
		return "redirect:/position/list.do";
	}
	
/*
 * 게시물 내용보기
 */
	// 게시물 상세 (완성)
	@RequestMapping("/position/detail.do")
	public ModelAndView boardDetail(@RequestParam int num)
	{
		// 해당 글의 조회수 증가
		positionService.updateView(num);
		// html 태그 불허
		PositionVO positionVO = positionService.selectBoard(num);
		positionVO.setPos_title(StringUtil.useNoHtml(positionVO.getPos_title()));
		positionVO.setPos_content(StringUtil.useBrNoHtml(positionVO.getPos_content()));
		return new ModelAndView("position_detail", "positionVO", positionVO);
	}
	
	// 이미지 출력
	@RequestMapping("/position/imageView.do")
	public ModelAndView imageView(@RequestParam int num)
	{
		log.debug("이미지 출력 호출");
		PositionVO positionVO = positionService.selectBoard(num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("uploadfile", positionVO.getPos_uploadfile());
		return mav;
	}
	
/*
 * 게시물 수정 (완성)
 */
	@RequestMapping(value="/position/modify.do", method=RequestMethod.GET)
	public String modifyForm(@RequestParam int num, Model model)
	{
		PositionVO positionVO = positionService.selectBoard(num);
		model.addAttribute("positionVO", positionVO);
		return "position_modify";
	}
	
	@RequestMapping(value="/position/modify.do", method=RequestMethod.POST)
	public String modifySubmit(@Valid PositionVO positionVO, BindingResult result, HttpServletRequest request)
	{
		// 유효성 오류가 있는 경우
		if(result.hasErrors()) return "position_modify";
		
		positionService.updateBoard(positionVO);
		return "redirect:/position/list.do";
	}
/*
 * 게시물 삭제
 */
	@RequestMapping("/position/delete.do")
	public String deleteSubmit(@RequestParam int num)
	{
		log.debug("삭제 호출");
		positionService.deleteBoard(num);
		return "redirect:/position/list.do";
	}
	
/*
 * 해당 게시물의 추천수 변경
 */
	public void updateFav(@RequestParam int num)
	{
		// 원버튼
		// positionService.updateFavUp(num);
		// positionService.updateFavDown(num);
	}
	
/*
 * 해당 게시물의 댓글수 증가,감소
 */
	public void updateCommentUp(@RequestParam int num)
	{
		positionService.updateCommentUp(num);
	}
	
	public void updateCommentDown(@RequestParam int num)
	{
		positionService.updateCommentDown(num);
	}
}
