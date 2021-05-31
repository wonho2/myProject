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
import kr.spring.position.vo.PositionCommentVO;
import kr.spring.position.vo.PositionFavVO;

@Controller
public class PositionAjaxController
{
	@Resource
	private PositionService positionService;
	
/*
 * 게시물 추천
 */
	@RequestMapping("/position/clickFav.do")
	@ResponseBody
	public Map<String, String> clickFav(@RequestParam int pos_num, HttpSession session)
	{
		Integer mem_num = (Integer)session.getAttribute("user_num");
		boolean clickedFav = false;
		
		Map<String, String> map = new HashMap<String, String>();
		if(mem_num == null)
		{
			map.put("result", "needLogin");
		}
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
	public Map<String,Object> selectList_recent(@RequestParam int pos_num, @RequestParam int sort_type, HttpSession session)
	{
		// 해당 게시물 댓글의 갯수
		int commentCount = positionService.selectCommentCount(pos_num);
		// 댓글 목록
		List<PositionCommentVO> commentList = Collections.emptyList();
		if(commentCount > 0)
		{
			if(sort_type == 1) commentList = positionService.selectCommentList(pos_num, sort_type);
		}
		// ajax에 전달할 map 객체
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("commentCount", commentCount);
		mapJson.put("commentList", commentList);
		// 현재 로그인 되어있는 회원 번호
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num != null)
		{
			mapJson.put("mem_num", mem_num);
		}
		return mapJson;
	}

/*
 * 댓글 쓰기
 */
	@RequestMapping("/position/writeComment.do")
	@ResponseBody
	public Map<String,String> writeComment(@RequestParam int pos_num, @RequestParam String poc_content, HttpSession session)
	{
		// 로그인 상태 여부 체크
		Map<String,String> map = new HashMap<String,String>();
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num == null)
		{
			map.put("result", "needLogin");
		}
		else
		{
			// vo 생성
			PositionCommentVO vo = new PositionCommentVO();
			vo.setPos_num(pos_num);
			vo.setMem_num(mem_num);
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
	public Map<String,String> modifyComment(@RequestParam int poc_num, @RequestParam int mem_num, @RequestParam String poc_content, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		Integer user_num = (Integer)session.getAttribute("user_num");
		// 로그인이 되어있지 않은 경우
		if(user_num == null)
		{
			map.put("result", "needLogin");
		}
		// 댓글 작성자와 로그인 되어있는 회원이 동일 인물인 경우
		else if(user_num == mem_num)
		{
			PositionCommentVO vo = new PositionCommentVO();
			vo.setPoc_num(poc_num);
			vo.setPoc_content(poc_content);
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
		if(user_num == null)
		{
			map.put("result", "needLogin");
		}
		else if(user_num == mem_num)
		{
			positionService.deleteComment(poc_num);
			map.put("result", "success");
		}
		else
		{	
			map.put("result", "notMatchUser");
		}		

		return map;
	}	
}
