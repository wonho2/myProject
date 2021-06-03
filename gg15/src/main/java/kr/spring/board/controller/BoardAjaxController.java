package kr.spring.board.controller;

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

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardReportVO;
import kr.spring.board.vo.BoardStatusVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardAjaxController{
	
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private BoardService boardService;
	
	//댓글 등록
	@RequestMapping("/board/writeReply.do")
	@ResponseBody
	public Map<String,String> writeComment(
			BoardReplyVO board_replyVO,
			HttpSession session,
			HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<board_replyVO>> : " + 
					board_replyVO);
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
			boardService.insertBoardComment(board_replyVO);
			map.put("result", "success");
		}

		return map;
	}
	
	//댓글 목록
	@RequestMapping("/board/listReply.do")
	@ResponseBody
	public Map<String,Object> getListBoardComment(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("boa_num") int boa_num){
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<boa_num>> : " + boa_num);
		}

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("boa_num", boa_num);

		//총 글의 갯수
		int count = boardService.selectBoardCommentCount(map);

		PagingUtil page = new PagingUtil(currentPage,count,
				rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<BoardReplyVO> list = null;
		if(count > 0) {
			list = boardService.selectBoardComment(map);
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
	@RequestMapping("/board/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteBoardComment(
			@RequestParam("bor_num") int bor_num,
			@RequestParam("mem_num") int mem_num,
			HttpSession session){
   
		if(log.isDebugEnabled()) {
			log.debug("<<bor_num>> : " + bor_num);
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
			boardService.deleteBoardComment(bor_num);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}		
		return map;
	}
	//댓글 수정
	@RequestMapping("/board/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyBoardComment(
			BoardReplyVO board_replyVO,
			HttpSession session,
			HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<board_replyVO>> : " + 
					board_replyVO);
		}

		Map<String,String> map = 
				new HashMap<String,String>();

		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_num!=null && user_num==board_replyVO.getMem_num()){
			//로그인 회원 번호와 작성자 회원번호 일치

			//댓글 수정
			boardService.updateBoardComment(board_replyVO);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		return map;
	}
	
	
	//게시물 좋아요
	@RequestMapping("/board/getFav.do")
	@ResponseBody
	public Map<String,Object> getFav(BoardFavVO fav,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 좋아요>> : " + fav);
		}

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();

		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapJson.put("result", "success");
			mapJson.put("status", "noFav");
			mapJson.put("count", boardService.selectFavCount(fav.getBoa_num()));
		}else {
			//로그인된 아이디 셋팅
			fav.setMem_num(user_num);

			BoardFavVO boardFav = boardService.selectFav(fav);

			if(boardFav!=null) {
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
				mapJson.put("count", boardService.selectFavCount(fav.getBoa_num()));
			}else {
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
				mapJson.put("count", boardService.selectFavCount(fav.getBoa_num()));
			}
		}
 
		return mapJson;
	}
	//부모글 좋아요 등록
	@RequestMapping("/board/writeFav.do")
	@ResponseBody
	public Map<String,Object> writeFav(BoardFavVO fav,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<부모글 좋아요 등록>> : " + fav);
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
				log.debug("<<부모글 좋아요 등록>> : " + fav);
			}
			
			BoardFavVO boardFav = boardService.selectFav(fav);

			if(boardFav!=null) {
				boardService.deleteFav(boardFav.getBof_num());

				map.put("result", "success");
				map.put("status", "noFav");
				map.put("count", boardService.selectFavCount(fav.getBoa_num()));
			}else {
				boardService.insertFav(fav);

				map.put("result", "success");
				map.put("status", "yesFav");
				map.put("count", boardService.selectFavCount(fav.getBoa_num()));
			}
		}
		return map;
	}
	
	//================================================================//	
	//게시물 신고 목록
/*
  	@RequestMapping("/board/getReport.do")
	@ResponseBody
	public Map<String,Object> getReport(BoardReportVO report,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 신고>> : " + report);
		}

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();

		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapJson.put("result", "success");
			mapJson.put("siren", "noReport");
		}else {
			//로그인된 아이디 셋팅
			report.setMem_num(user_num);

			BoardReportVO boardReport = boardService.selectReport(report);

			if(boardReport!=null) {
				mapJson.put("result", "success");
				mapJson.put("siren", "yesReport");
			}else {
				mapJson.put("result", "success");
				mapJson.put("siren", "noReport");
			}
		}
 
		return mapJson;
	}
	// 신고 등록
	@RequestMapping("/board/writeReport.do")
	@ResponseBody
	public Map<String,Object> writeReport(BoardReportVO report,HttpSession session,
										  HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<부모글 신고 등록>> : " + report);
		}

		Map<String,Object> map = 
				new HashMap<String,String>();

		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			map.put("result", "logout");
		}else {

			//댓글 등록
			boardService.insertReport(BoardReportVO);
			map.put("result", "success");
		}

		return map;
	}
*/
	//================================================================//
/*	
	//게시물 차단
	@RequestMapping("/board/getStatus.do")
	@ResponseBody
	public Map<String,Object> getStatus(BoardStatusVO cate,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<게시판 차단>> : " + status);
		}

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();

		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapJson.put("result", "success");
			mapJson.put("status", "noStatus");
		}else {
			//로그인된 아이디 셋팅
			status.setMem_num(user_num);

			BoardStatusVO boardStatus = boardService.selectStatus(status);

			if(boardStatus!=null) {
				mapJson.put("result", "success");
				mapJson.put("status", "yesStatus");
			}else {
				mapJson.put("result", "success");
				mapJson.put("status", "noStatus");
			}
		}
 
		return mapJson;
	}
	//부모글 차단 등록
	@RequestMapping("/board/writeStatus.do")
	@ResponseBody
	public Map<String,Object> writeStatus(BoardStatusVO fav,HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<부모글 차단 등록>> : " + Status);
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
				log.debug("<<부모글 차단 등록>> : " + status);
			}
			
			BoardStatusVO boardStatus = boardService.selectStatus(Status);

			if(boardStatus!=null) {
				boardService.deleteStatus(boardStatus.getBoa_status());

				map.put("result", "success");
				map.put("status", "noStatus");
			}else {
				boardService.insertStatus(status);

				map.put("result", "success");
				map.put("status", "yesStatus");
			}
		}
		return map;
	}
*/
}