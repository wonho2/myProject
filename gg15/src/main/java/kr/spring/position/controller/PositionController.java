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
	private Logger log = Logger.getLogger(this.getClass());
	
/*
 * 상수
 */
	// 게시물 정렬 상수 (데이터베이스 속성명)
	private class SortType
	{
		private SortType() {}
		static final String RECENT = "p.pos_num";
		static final String POPULAR = "p.pos_view"; // 조회수가 높을수록 인기글로 설정
	}
	
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
// 공통 (게시물 최신순, 인기순 정렬) : (현재 페이지, 정렬 방식, 게시물 포지션 타입)
	private ModelAndView getBoardList(int currentPage, String sortAttrName, PositionType type)
	{
		// 게시물 개수
		int boardCount = positionService.selectBoardCount(type.getValue());
		log.debug("게시물 개수 : " + boardCount);
		// 페이징 처리 정보 저장
		PagingUtil page = new PagingUtil(currentPage, boardCount, 3, 3, "list.do");
		List<PositionVO> boardList = null;
		if(boardCount > 0)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			// DAO에 넘길 정렬방식, 포지션타입 저장
			map.put("sortAttr", sortAttrName);
			map.put("typeValue", type.getValue());
			boardList = positionService.selectBoardList(map);
			log.debug("게시물 목록 : " + boardList);
		}
		// 모델, 뷰 정보 저장
		ModelAndView mav = new ModelAndView();
		mav.setViewName("position_list");
		mav.addObject("boardCount", boardCount);
		mav.addObject("boardList", boardList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		mav.addObject("pos_type", type.getValue());
		return mav;
	}
	
// 게시물 리스트 : 전체보기 (default)
	@RequestMapping("/position/list.do")
	public ModelAndView boardList_all(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.RECENT, PositionType.ALL);
	}
	
	@RequestMapping("/position/list_popular.do")
	public ModelAndView boardList_all_pop(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.POPULAR, PositionType.ALL);
	}
	
// 게시물 리스트 : 탑
	@RequestMapping("/position/list_top.do")
	public ModelAndView boardList_top(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.RECENT, PositionType.TOP);
	}
	
	@RequestMapping("/position/list_top_popular.do")
	public ModelAndView boardList_top_pop(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.POPULAR, PositionType.TOP);
	}
	
// 게시물 리스트 : 정글
	@RequestMapping("/position/list_jungle.do")
	public ModelAndView boardList_jungle(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.RECENT, PositionType.JUNGLE);
	}
	
	@RequestMapping("/position/list_jungle_popular.do")
	public ModelAndView boardList_jungle_pop(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.POPULAR, PositionType.JUNGLE);
	}
	
// 게시물 리스트 : 미드
	@RequestMapping("/position/list_mid.do")
	public ModelAndView boardList_mid(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.RECENT, PositionType.MID);
	}
	
	@RequestMapping("/position/list_mid_popular.do")
	public ModelAndView boardList_mid_pop(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.POPULAR, PositionType.MID);
	}
	
// 게시물 리스트 : 원딜
	@RequestMapping("/position/list_ad.do")
	public ModelAndView boardList_ad(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.RECENT, PositionType.AD);
	}
	
	@RequestMapping("/position/list_ad_popular.do")
	public ModelAndView boardList_ad_pop(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.POPULAR, PositionType.AD);
	}
	
// 게시물 리스트 : 서포터
	@RequestMapping("/position/list_support.do")
	public ModelAndView boardList_support(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.RECENT, PositionType.SUPPORT);
	}
	
	@RequestMapping("/position/list_support_popular.do")
	public ModelAndView boardList_support_pop(@RequestParam(value="page", defaultValue="1") int currentPage)
	{
		return getBoardList(currentPage, SortType.POPULAR, PositionType.SUPPORT);
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
		ModelAndView mav = new ModelAndView();
		// 해당 글의 조회수 증가
		positionService.updateView(pos_num);
		// html 태그 불허
		PositionVO positionVO = positionService.selectBoard(pos_num);
		positionVO.setPos_title(StringUtil.useNoHtml(positionVO.getPos_title()));
		positionVO.setPos_content(StringUtil.useBrNoHtml(positionVO.getPos_content()));
		mav.setViewName("position_detail");
		// 게시물 vo
		mav.addObject("positionVO", positionVO);
		// 추천수
		mav.addObject("favCount", positionService.selectFavCount(pos_num));
		return mav;
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
