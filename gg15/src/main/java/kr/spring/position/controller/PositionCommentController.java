package kr.spring.position.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.position.service.PositionCommentService;
import kr.spring.position.vo.PositionCommentVO;

@Controller
public class PositionCommentController
{
	@Resource
	private PositionCommentService positionCommentService;
	
/*
 * 자바빈 초기화
 */
	@ModelAttribute("positionCommentVO")
	public PositionCommentVO initCommand()
	{
		return new PositionCommentVO();
	}
		
/*
 * 해당 게시물 댓글 쓰기
 */
	@RequestMapping("/position/writeComment.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam String poc_content, HttpServletRequest request, HttpSession session)
	{
		Map<String,String> map = new HashMap<String,String>();
		PositionCommentVO vo = new PositionCommentVO();
		// 게시물 번호
		int pos_num = Integer.parseInt(request.getParameter("pos_num")); // detail?pos_num=${positionVO.pos_num}
		vo.setPos_num(pos_num);
		// 댓글 작성자 회원번호
		int mem_num = (Integer)session.getAttribute("user_num");
		vo.setMem_num(mem_num);
		// 댓글 내용
		vo.setPoc_content(poc_content);
		// ajax에 json 데이터 넘겨서 통신
		boolean isSuccessed = positionCommentService.insertComment(vo);
		if(isSuccessed)
		{
			map.put("result", "successed");
		}
		else
		{
			map.put("result", "failed");
		}
		
		return map;
	}
}
