package kr.spring.position.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.position.etc.PositionType;
import kr.spring.position.service.PositionService;
import kr.spring.position.vo.PositionVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class PositionController
{
	@Resource
	private PositionService positionService;
	
/*
 * 자바빈 초기화
 */
	@ModelAttribute("positionVO")
	public PositionVO initCommand()
	{
		return new PositionVO();
	}

/*
 * 게시물 목록
 */
// 공통
	private ModelAndView boardList(int currentPage, PositionType type)
	{
		// 페이징 처리
		int boardCount = positionService.selectBoardCount(type);
		PagingUtil page = new PagingUtil(currentPage, boardCount, 3, 3, "list.do");
		List<PositionVO> boardList = null;
		if(boardCount > 0)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			boardList = positionService.selectBoardList(map, type); 
		}
		// 데이터 저장 및 리턴
		ModelAndView mav = new ModelAndView();
		mav.setViewName("position_list");
		mav.addObject("boardCount", boardCount);
		mav.addObject("boardList", boardList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		return mav;  
	}
	
// 게시물 리스트 : 전체보기 (default)
	@RequestMapping("/position/list.do")
	public ModelAndView boardList_all(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return boardList(currentPage, PositionType.ALL);
	}
	
// 게시물 리스트 : 탑
	@RequestMapping("/position/list_top.do")
	public ModelAndView boardList_top(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return boardList(currentPage, PositionType.TOP);
	}
	
// 게시물 리스트 : 정글
	@RequestMapping("/position/list_jungle.do")
	public ModelAndView boardList_jungle(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return boardList(currentPage, PositionType.JUNGLE);
	}
	
// 게시물 리스트 : 미드
	@RequestMapping("/position/list_mid.do")
	public ModelAndView boardList_mid(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return boardList(currentPage, PositionType.MID);
	}
	
// 게시물 리스트 : 탑
	@RequestMapping("/position/list_ad.do")
	public ModelAndView boardList_ad(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return boardList(currentPage, PositionType.AD);
	}
	
// 게시물 리스트 : 서포터
	@RequestMapping("/position/list_support.do")
	public ModelAndView boardList_support(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return boardList(currentPage, PositionType.SUPPORT);
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
	public String writeSubmit(@Valid PositionVO positionVO, HttpServletRequest request, HttpSession session)
	{
		// 정보 셋팅
		Integer mem_num = (Integer)session.getAttribute("user_num");
		positionVO.setMem_num(mem_num);
		// 글쓰기
		positionService.insertBoard(positionVO);
		return "redirect:/position/list.do";
	}
	
/*
 * 게시물 내용보기 (완성)
 */
	// 게시물 상세
	@RequestMapping("/position/detail.do")
	public ModelAndView boardDetail(@RequestParam int pos_num)
	{
		// 해당 글의 조회수 증가
		positionService.updateView(pos_num);
		// html 태그 불허
		PositionVO positionVO = positionService.selectBoard(pos_num);
		positionVO.setPos_title(StringUtil.useNoHtml(positionVO.getPos_title()));
		positionVO.setPos_content(StringUtil.useBrNoHtml(positionVO.getPos_content()));
		return new ModelAndView("position_detail", "positionVO", positionVO);
	}
	
	// 이미지 출력
	@RequestMapping("/position/imageView.do")
	public ModelAndView imageView(@RequestParam int pos_num)
	{
		PositionVO positionVO = positionService.selectBoard(pos_num);
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		// ImageView.java에 있는 이름(imageFile, filename) 똑같이 써야함 무조건!!
		mav.addObject("imageFile", positionVO.getPos_uploadfile());
		mav.addObject("filename", positionVO.getPos_filename());
		return mav;
	}
	
/*
 * 게시물 수정 (완성)
 */
	@RequestMapping(value="/position/modify.do", method=RequestMethod.GET)
	public String modifyForm(@RequestParam int pos_num, Model model)
	{
		PositionVO positionVO = positionService.selectBoard(pos_num);
		model.addAttribute("positionVO", positionVO);
		return "position_modify";
	}
	
	@RequestMapping(value="/position/modify.do", method=RequestMethod.POST)
	public String modifySubmit(@Valid PositionVO positionVO, HttpServletRequest request)
	{
		positionService.updateBoard(positionVO);
		return "redirect:/position/list.do";
	}
/*
 * 게시물 삭제 (완성)
 */
	@RequestMapping("/position/delete.do")
	public String deleteSubmit(@RequestParam int pos_num)
	{
		positionService.deleteBoard(pos_num);
		return "redirect:/position/list.do";
	}
}
