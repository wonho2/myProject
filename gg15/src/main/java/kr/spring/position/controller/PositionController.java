package kr.spring.position.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PositionController
{
	@RequestMapping("/position/list.do")
	public String position_main()
	{
		return "position_list";
	}
}
