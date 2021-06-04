package kr.spring.manualtool.controller;

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

import kr.spring.manualtool.vo.ManualtoolFavVO;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.manualtool.service.ManualtoolService;
import kr.spring.manualtool.vo.ManualtoolCommentVO;
import kr.spring.util.PagingUtil;

@Controller
public class ManualtoolAjaxController {

	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private ManualtoolService manualtoolService;

	//댓글 등록
	@RequestMapping("/manualTool/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(ManualtoolCommentVO manualtoolCommentVO,
										HttpSession session,
										HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug("<<manualtoolCommentVO>> : " + manualtoolCommentVO);
		}

		Map<String, String> map = new HashMap<String, String>();

		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {
			//로그인 안 됨
			map.put("result", "logout");
		} else {
			//댓글 등록
			manualtoolService.insertReply(manualtoolCommentVO);
			map.put("result", "success");
		}

		return map;
	}

	//댓글 목록
	@RequestMapping("/manualTool/listReply.do")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			                           @RequestParam("man_num") int man_num) {
		if (log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<man_num>> : " + man_num);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("man_num", man_num);

		// 총 글의 갯수
		int count = manualtoolService.selectRowCountReply(map);

		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<ManualtoolCommentVO> list = null;
		if (count > 0) {
			list = manualtoolService.selectListReply(map);
		} else {
			list = Collections.emptyList();
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);

		return mapJson;
	}

	//댓글 삭제
	@RequestMapping("/manualTool/deleteReply.do")
	@ResponseBody
	public Map<String, String> deleteReply(@RequestParam("mar_num") int mar_num, @RequestParam("mem_num") int mem_num, HttpSession session) {

		if (log.isDebugEnabled()) {
			log.debug("<<mar_num>> : " + mar_num);
			log.debug("<<mem_num>> : " + mem_num);
		}

		Map<String, String> map = new HashMap<String, String>();

		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {
			//로그인이 되어있지 않음
			map.put("result", "logout");
		} else if (user_num != null && user_num == mem_num) {
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			manualtoolService.deleteReply(mar_num);
			map.put("result", "success");
		} else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		return map;
	}

	//댓글 수정
	@RequestMapping("/manualTool/updateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(ManualtoolCommentVO manualtoolCommentVO,
											HttpSession session,
											HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug("<<manualtoolCommentVO>> : " + manualtoolCommentVO);
		}

		Map<String, String> map = new HashMap<String, String>();

		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		} else if (user_num != null && user_num == manualtoolCommentVO.getMem_num()) {
			//로그인 회원 번호와 작성자 회원번호 일치

			//댓글 수정
			manualtoolService.updateReply(manualtoolCommentVO);
			map.put("result", "success");
		} else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}

		return map;
	}
	
	//게시물 좋아요
		@RequestMapping("/manualTool/getFav.do")
		@ResponseBody
		public Map<String,Object> getFav(ManualtoolFavVO fav, HttpSession session){

			if(log.isDebugEnabled()) {
				log.debug("<<게시물 좋아요>> : " + fav);
			}

			Map<String,Object> mapJson = 
					new HashMap<String,Object>();

			Integer user_num = (Integer)session.getAttribute("user_num");
			if(user_num==null) {
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
				mapJson.put("count", manualtoolService.selectFavCount(fav.getMan_num()));
			}else {
				//로그인된 아이디 셋팅
				fav.setMem_num(user_num);

				ManualtoolFavVO manualtoolFav = manualtoolService.selectFav(fav);

				if(manualtoolFav!=null) {
					mapJson.put("result", "success");
					mapJson.put("status", "yesFav");
					mapJson.put("count", manualtoolService.selectFavCount(fav.getMan_num()));
				}else {
					mapJson.put("result", "success");
					mapJson.put("status", "noFav");
					mapJson.put("count", manualtoolService.selectFavCount(fav.getMan_num()));
				}
			}
	 
			return mapJson;
		}
		//부모글 좋아요 등록
		@RequestMapping("/manualTool/writeFav.do")
		@ResponseBody
		public Map<String,Object> writeFav(ManualtoolFavVO fav,HttpSession session){

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
				
				ManualtoolFavVO boardFav = manualtoolService.selectFav(fav);

				if(boardFav!=null) {
					manualtoolService.deleteFav(boardFav.getMaf_num());

					map.put("result", "success");
					map.put("status", "noFav");
					map.put("count", manualtoolService.selectFavCount(fav.getMan_num()));
				}else {
					manualtoolService.insertFav(fav);

					map.put("result", "success");
					map.put("status", "yesFav");
					map.put("count", manualtoolService.selectFavCount(fav.getMan_num()));
				}
			}
			return map;
		}

}
