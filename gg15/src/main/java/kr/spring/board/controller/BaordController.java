package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.manualtool.vo.ManualtoolVO;
import kr.spring.position.vo.PositionVO;
import kr.spring.util.PagingUtil;

@Controller
public class BaordController {	
	private Logger log = Logger.getLogger(this.getClass());

	//의존 관계 설정
	@Resource
	private BoardService boardService;
	
	//자바빈 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}	
		
	//자유게시판  목록
	@RequestMapping("/board/list.do")
	public ModelAndView process(
	@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
		
		//총 레코드 수
		//int count = boardService.selectRowCount();
		if(log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			//log.debug("<<count>> : " + count);
		}
		
		//페이징 처리
		/*PagingUtil page = new PagingUtil(currentPage,count,10,10,"list.do");
		List<BoardVO> list = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			list = boardService.selectList(map);
		}*/
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		//mav.addObject("count", count);
		//mav.addObject("list",list);
		//mav.addObject("pagingHtml",page.getPagingHtml());
		   
		return mav;
	}
	
	
	//자유게시판 게시글 등록
		//등록 폼
	@RequestMapping(value="/board/boardWrite.do", method=RequestMethod.GET)
	public String writeForm()
	{
		return "boardWrite";
	}
	
	@RequestMapping(value="/board/boardWrite.do", method=RequestMethod.POST)
	public String writeSubmit(@Valid BoardVO boardVO, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		// 유효성 오류가 있는 경우
		if(result.hasErrors()) return writeForm();
		// 정보 셋팅
		//Integer mem_num = (Integer)session.getAttribute("mem_num");
		//boardVO.setMem_num(mem_num);
		// 글쓰기
		boardService.insertBoard(boardVO);
		return "redirect:/board/list.do";
	}

		
	

		
		
	
}
