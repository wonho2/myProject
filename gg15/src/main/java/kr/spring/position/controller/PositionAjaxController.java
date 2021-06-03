package kr.spring.position.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.position.service.PositionService;
import kr.spring.position.vo.PositionCommentFavVO;
import kr.spring.position.vo.PositionCommentVO;
import kr.spring.position.vo.PositionFavVO;
import kr.spring.util.PagingUtil;

@Controller
public class PositionAjaxController
{
	@Resource
	private PositionService positionService;
	
	private static final int RowCount = 3;
	private static final int PageCount = 3;
	
/*
 * 게시물 추천
 */
	@RequestMapping("/position/clickFav.do")
	@ResponseBody
	public Map<String, String> clickFav(@RequestParam int pos_num, HttpSession session)
	{
		Map<String, String> map = new HashMap<String, String>();
		Integer mem_num = (Integer)session.getAttribute("user_num");
		boolean clickedFav = false;
		// 회원 로그인이 되어있지 않은 경우
		if(mem_num == null)
		{
			map.put("result", "needLogin");
		}
		// 회원 로그인이 되어있는 경우
		else if(clickedFav = positionService.selectClickedFav(pos_num, mem_num))
		{
			// vo 객체 생성
			PositionFavVO vo = new PositionFavVO();
			vo.setPos_num(pos_num);
			vo.setMem_num(mem_num);
			// 이미 해당 게시물의 추천버튼을 누른 경우
			if(clickedFav)
			{
				positionService.deleteFav(vo);
				map.put("result", "success - favCancel");
			}
			// 추천버튼을 누르지 않은 경우
			else
			{
				positionService.insertFav(vo);
				map.put("result", "success - favUp");
			}
			// 변경된 추천 수
			String favCount = Integer.toString(positionService.selectFavCount(pos_num));
			map.put("favCount", favCount);
		}

		return map;
		
	}
	
/*
 * 댓글 리스트 가져오기 (게시물 번호, 정렬 타입)
 */
	@RequestMapping("/position/commentList_recent.do")
	@ResponseBody
	public Map<String,Object> selectList_recent(@RequestParam(value="pageNum",defaultValue="1") int currentPage, @RequestParam int pos_num, @RequestParam int sort_type, HttpSession session)
	{
		// 페이징 처리
		Map<String, Object> listMap = new HashMap<String, Object>();
		int commentCount = positionService.selectCommentCount(pos_num);
		PagingUtil page = new PagingUtil(currentPage, commentCount, RowCount, PageCount, null);
		listMap.put("pos_num", pos_num);
		listMap.put("sort_type", sort_type);
		listMap.put("start", page.getStartCount());
		listMap.put("end", page.getEndCount());
		
		// 현재 로그인 상태인지 세션으로 확인
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num != null) listMap.put("mem_num", mem_num);
		else listMap.put("mem_num", -1);
		
		// 댓글 목록
		List<PositionCommentVO> commentList = Collections.emptyList();
		if(commentCount > 0)
		{
			commentList = positionService.selectCommentList(listMap);
		}
		// ajax에 전달할 map 객체
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("commentCount", commentCount);
		mapJson.put("rowCount", RowCount);
		mapJson.put("commentList", commentList);
		
		return mapJson;
	}

/*
 * 댓글 쓰기
 */
	@RequestMapping("/position/writeComment.do")
	@ResponseBody
	public Map<String,String> writeComment(PositionCommentVO vo, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		// 로그인 상태 여부 체크
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num == null)
		{
			map.put("result", "needLogin");
		}
		else
		{
			positionService.insertComment(vo);
			map.put("result", "success");
		}
		return map;
	}

/*
 * 댓글 수정
 */
	@RequestMapping("/position/modifyComment.do")
	@ResponseBody
	public Map<String,String> modifyComment(PositionCommentVO vo, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		Integer user_num = (Integer)session.getAttribute("user_num");
		// 로그인이 되어있지 않은 경우
		if(user_num == null)
		{
			map.put("result", "needLogin");
		}
		// 댓글 작성자와 로그인 되어있는 회원이 동일 인물인 경우
		else if(user_num == vo.getMem_num())
		{
			positionService.modifyComment(vo);
			map.put("result", "success");
		}
		// 댓글 작성자와 로그인 되어있는 회원이 다른 인물인 경우
		else
		{
			map.put("result", "notMatchUser");
		}
		return map;
	}

/*
 * 댓글 삭제
 */
	@RequestMapping("/position/deleteComment.do")
	@ResponseBody
	public Map<String,String> deleteComment(@RequestParam int poc_num, @RequestParam int mem_num, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		Integer user_num = (Integer)session.getAttribute("user_num");
		// 로그인이 되어있지 않은 경우
		if(user_num == null)
		{
			map.put("result", "needLogin");
		}
		// 댓글 작성자와 현재 로그인한 회원 정보가 일치하는 경우
		else if(user_num == mem_num)
		{
			positionService.deleteComment(poc_num);
			map.put("result", "success");
		}
		// 댓글 작성자와 로그인 되어있는 회원이 다른 경우
		else
		{	
			map.put("result", "notMatchUser");
		}		

		return map;
	}
	
/*
 * 댓글 추천, 비추천 (원버튼)
 */
	@RequestMapping("/position/commentFav.do")
	@ResponseBody
	public Map<String,String> commentFav(@RequestParam int poc_num, @RequestParam int mem_num, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		Integer user_num = (Integer)session.getAttribute("user_num");
		boolean isClicked = false;
		// 로그인이 되어있지 않은 경우
		if(user_num == null)
		{
			map.put("result", "needLogin");
		}
		// 추천 버튼을 이전에 눌렀었는지 확인
		else if(isClicked = positionService.selectClickedCommentFav(poc_num, mem_num))
		{
			// vo 객체 생성
			PositionCommentFavVO vo = new PositionCommentFavVO();
			vo.setPoc_num(poc_num);
			vo.setMem_num(mem_num);
			// 이미 해당 게시물의 추천버튼을 누른 경우
			if(isClicked)
			{
				positionService.deleteCommentFav(vo);
				map.put("result", "success - favCancel");
			}
			// 추천버튼을 누르지 않은 경우
			else
			{
				positionService.insertCommentFav(vo);
				map.put("result", "success - favUp");
			}
			// 변경된 추천 수
			String commentFavCount = Integer.toString(positionService.selectCommentFavCount(poc_num));
			map.put("commentFavCount", commentFavCount);
		}
		
		return map;
	}
}
