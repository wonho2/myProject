package kr.spring.news.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.news.service.NewsService;
import kr.spring.news.vo.NewsVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
    
@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());
	
	//의존 관계 설정 
	@Resource
	private NewsService newsService;
	
	//자바빈(VO) 초기화
	@ModelAttribute("newsVO")
	public NewsVO initCommand() {
		return new NewsVO();
	} 
	//====게시판 글 작성=======// 
	//등록 폼
	@RequestMapping(value="/news/write.do",method=RequestMethod.GET)
	public String form() {
		return "newsWrite";
	} 
	//전송된 데이터 처리 
	@RequestMapping(value="/news/write.do",method=RequestMethod.POST)
	public String submit(@Valid NewsVO newsVO,
			             BindingResult result,
			             HttpServletRequest request,
			             HttpSession session) {
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "newsWrite";
		}
		
		//회원 번호 셋팅
		newsVO.setMem_num((Integer)session.getAttribute("user_num"));
		//글쓰기
		newsService.insertNews(newsVO);
		
		return "redirect:/news/list.do";
	}
	
	//=====게시판 글 목록=====//
	@RequestMapping("/news/list.do")
	public ModelAndView newsList(
	@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
			//총 레코드 수
		int count = newsService.selectNewsCount();	
		if(log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<count>> : " + count);
		}

		//페이징 처리
		PagingUtil page = 
				new PagingUtil(currentPage,count,10,10,"list.do");
	
		List<NewsVO> newsList = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			newsList = newsService.selectList(map);
		}
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newsList");
		mav.addObject("count", count);
		mav.addObject("newsList",newsList);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
	
	//====게시판 글 상세======//
	@RequestMapping("/news/detail.do")
	public ModelAndView detail(@RequestParam int new_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<new_num>> : " + new_num);
		}
		
		//해당 글의 조회수 증가
		newsService.updateHit(new_num);
		
		NewsVO news = newsService.selectNews(new_num);
		//HTML 태그 불허
		news.setNew_title(StringUtil.useNoHtml(news.getNew_title()));
		//HTML 태그 불허 및 줄바꿈 처리
		news.setNew_content(StringUtil.useBrNoHtml(news.getNew_content()));
		
		return new ModelAndView("newsView","news",news);
		
	}
	//이미지 출력
	@RequestMapping("/news/imageView.do")
	public ModelAndView viewImage(@RequestParam int new_num) {
		NewsVO news = newsService.selectNews(new_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",news.getNew_uploadfile());
		mav.addObject("filename", news.getNew_filename());
		
		return mav;
	}
	
	//=====게시판 글 수정======//
	//수정 폼
	@RequestMapping(value="/news/update.do",method=RequestMethod.GET)
	public String formUpdate(@RequestParam int new_num,Model model) {
		NewsVO newsVO = newsService.selectNews(new_num);
		model.addAttribute("newsVO", newsVO);
		
		return "newsModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/news/update.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid NewsVO newsVO,
			                   BindingResult result,
			                   HttpServletRequest request) {
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "newsModify";
		}
		
		//글 수정
		newsService.updateNews(newsVO);
		
		return "redirect:/news/list.do";
	}
	
	//======게시판 글 삭제========//
	@RequestMapping("/news/delete.do")
	public String submitDelete(@RequestParam int new_num) {
		//글 삭제
		newsService.deleteNews(new_num);
		
		return "redirect:/news/list.do";
	}
	
}