package kr.spring.party.controller;

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
 
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyReplyVO;
import kr.spring.util.PagingUtil;

@Controller
public class PartyAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private PartyService partyService;

	//댓글 등록
	@RequestMapping("/party/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(
			PartyReplyVO partyReplyVO,
			HttpSession session,
			HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<PartyReplyVO>> : " + 
					partyReplyVO);
		}

		Map<String,String> map = 
				new HashMap<String,String>();
		
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//로그인 안 됨
			map.put("result", "logout");
		}else {
			//댓글 등록
			partyService.insertPartyReply(partyReplyVO);
			map.put("result", "success");
		}

		return map;
	}
	//댓글 목록
	@RequestMapping("/party/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("par_num") int par_num,
			HttpSession session){
		//(******주의)댓글 좋아요 처리시만 HttpSession 넣을 것
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<par_num>> : " + par_num);
		}

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("par_num", par_num);

		//총 글의 갯수
		int count = partyService.selectPartyRowCountReply(map);

		PagingUtil page = new PagingUtil(currentPage,count,
				rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		//(***주의)댓글 좋아요 처리할 경우만
		map.put("par_num", par_num);
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num!=null) {
			map.put("mem_num", user_num);
		}else {
			map.put("mem_num", 0); 
		}
		List<PartyReplyVO> list = null;
		if(count > 0) {
			list = partyService.selectPartyListReply(map);
		}else {
			list = Collections.emptyList();
		}

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);

		return mapJson;
	}
	//댓글 삭제
	@RequestMapping("/party/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(
			@RequestParam("pop_num") int pop_num,
			@RequestParam("mem_num") int mem_num,
			HttpSession session){
   
		if(log.isDebugEnabled()) {
			log.debug("<<pop_num>> : " + pop_num);
			log.debug("<<mem_num>> : " + mem_num);
		}

		Map<String,String> map = 
				new HashMap<String,String>();

		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_num!=null && user_num==mem_num) {
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			partyService.deletePartyReply(pop_num);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}		
		return map;
	}
	//댓글 수정
	@RequestMapping("/party/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(
			PartyReplyVO partyReplyVO,
			HttpSession session,
			HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<PartyReplyVO>> : " + 
					partyReplyVO);
		}

		Map<String,String> map = 
				new HashMap<String,String>();

		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_num!=null && user_num==partyReplyVO.getMem_num()){
			//로그인 회원 번호와 작성자 회원번호 일치

			//댓글 수정
			partyService.updatePartyReply(partyReplyVO);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}

		return map;
	}
	   
	//===========게시글 추천===============//
	//추천 읽기
	@RequestMapping("/party/getFav.do")
	@ResponseBody
	public Map<String,Object> getFav(PartyFavVO fav,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 좋아요>> : " + fav);
		}

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();

		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapJson.put("result", "success");
			mapJson.put("status", "noFav");
			mapJson.put("count", partyService.selectFavCount(fav.getPar_num()));
		}else {
			//로그인된 아이디 셋팅
			fav.setMem_num(user_num);

			PartyFavVO boardFav = partyService.selectFav(fav);

			if(boardFav!=null) {
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
				mapJson.put("count", partyService.selectFavCount(fav.getPar_num()));
			}else {
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
				mapJson.put("count", partyService.selectFavCount(fav.getPar_num()));
			}
		}

		return mapJson;
	}
	//추천 등록
	@RequestMapping("/party/writeFav.do")
	@ResponseBody
	public Map<String,Object> writeFav(PartyFavVO fav,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<부모글 좋아용 등록>> : " + fav);
		}

		Map<String,Object> map = 
				new HashMap<String,Object>();

		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			map.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user_num);

			if(log.isDebugEnabled()) {
				log.debug("<<부모글 좋아용 등록>> : " + fav);
			}
			
			PartyFavVO boardFav = partyService.selectFav(fav);

			if(boardFav!=null) {
				partyService.deleteFav(boardFav.getFav_num());

				map.put("result", "success");
				map.put("status", "noFav");
				map.put("count", partyService.selectFavCount(fav.getPar_num()));
			}else {
				partyService.insertFav(fav);

				map.put("result", "success");
				map.put("status", "yesFav");
				map.put("count", partyService.selectFavCount(fav.getPar_num()));
			}
		}
		return map;
	}
}












