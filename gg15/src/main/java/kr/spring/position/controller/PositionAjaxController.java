package kr.spring.position.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.position.service.PositionService;
import kr.spring.position.vo.PositionCommentVO;
import kr.spring.position.vo.PositionCommentFavVO;
import kr.spring.util.PagingUtil;

@Controller
public class PositionAjaxController
{
	@Resource
	private PositionService positionService;
/*
 * 댓글 리스트 가져오기
 */
	@RequestMapping("/position/commentList.do")
	@ResponseBody
	public Map<String,Object> selectList(@RequestParam int pos_num)
	{
		// 해당 게시물 댓글의 갯수
		int count = positionService.selectCommentCount(pos_num);
		// 댓글 목록
		List<PositionCommentVO> list = Collections.emptyList();
		if(count > 0) {
			list = positionService.selectCommentList(pos_num);
		}
		// ajax에 전달할 map 객체
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("commentList", list);
		return mapJson;
	}

/*
 * 댓글 쓰기
 */
	@RequestMapping("/position/writeComment.do")
	@ResponseBody
	public Map<String,String> writeComment(PositionCommentVO positionCommentVO, HttpSession session)
	{
		// 로그인 상태 여부 체크
		Map<String,String> map = new HashMap<String,String>();
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num == null) {
			map.put("result", "needLogin");
		}
		else {
			positionService.insertComment(positionCommentVO);
			map.put("result", "success");
		}
		return map;
	}

/*
 * 댓글 수정
 */
	@RequestMapping("/position/modifyComment.do")
	@ResponseBody
	public Map<String,String> modifyComment(PositionCommentVO positionCommentVO, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num == null) {
			map.put("result", "needLogin");
		}
		else if(mem_num == positionCommentVO.getMem_num()) {
			positionService.modifyComment(positionCommentVO);
			map.put("result", "success");
		}
		else{
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
		}else if(user_num == mem_num)
		{
			positionService.deleteComment(poc_num);
			map.put("result", "success");
		}else
		{
			map.put("result", "notMatchUser");
		}		

		return map;
	}	
}
