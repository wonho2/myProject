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
		@RequestMapping(value="/boarad/Write.do",method=RequestMethod.GET)
		public String writeForm() {
			return "boardWrite";
		}
		//전송된 데이터 처리
		@RequestMapping(value="/board/Write.do",method=RequestMethod.POST)
		public String writeSubmit(@Valid BoardVO BoardVO,
				             BindingResult result,
				             HttpServletRequest request,
				             HttpSession session) {
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				return "writeForm";
			}
			
			//회원 번호 셋팅
			//BoardVO.setMem_num((Integer)session.getAttribute("mem_num"));
			//글쓰기
			boardService.insertBoard(BoardVO);
			
			return "redirect:/board/list.do";
		}
		
	

		
		
	
}
