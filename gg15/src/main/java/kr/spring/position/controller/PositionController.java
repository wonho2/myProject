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
	// 내용 출력 및 페이지 이동
	@RequestMapping("/position/detail.do")
	public ModelAndView position_detail(@RequestParam("num") int num)
	{
		ModelAndView mav = new ModelAndView();
		// 게시물 내용 보여주는 코드 작성
		return mav;
	}
	
	// 이미지 출력
	@RequestMapping("/position/imageView.do")
	public ModelAndView imageView(@RequestParam int num)
	{
		ModelAndView mav = new ModelAndView();
		// 코드 작성
		mav.setViewName("imageView");
		return mav;
	}
	
/*
 * 게시물 수정
 */
	@RequestMapping(value="/position/modify.do", method=RequestMethod.GET)
	public String position_modify()
	{
		return "position_modify";
	}
	
/*
 * 게시물 삭제
 */
	@RequestMapping("/position/delete.do")
	public String deleteSubmit(@RequestParam int num)
	{
		// 삭제 코드 작성
		return "redirect:/position/list.do";
	}
}
