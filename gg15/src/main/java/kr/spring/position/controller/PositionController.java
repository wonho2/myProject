package kr.spring.position.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
/*
 * 게시판 글쓰기
 */
	@RequestMapping(value="/position/write.do", method=RequestMethod.GET)
	public String position_write()
	{
		return "position_write";
	}
	
/*
 * 게시물 내용보기
 */
	@RequestMapping("/position/detail.do")
	public ModelAndView position_detail(@RequestParam("num") int num)
	{
		ModelAndView mav = new ModelAndView();
		// 게시물 내용 보여주는 코드 작성
		return mav;
	}
}
