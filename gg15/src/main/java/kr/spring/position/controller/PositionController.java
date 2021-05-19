package kr.spring.position.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PositionController
{

/*
 * 게시물 목록
 */
	// 전체보기
	@RequestMapping("/position/list.do")
	public String position_list()
	{
		// 게시물 정렬 코드 작성
		return "position_list";
	}

}
