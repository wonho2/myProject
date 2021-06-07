package kr.spring.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.position.etc.PositionType;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class BoardController {	
	private Logger log = Logger.getLogger(this.getClass());

	//의존 관계 설정
	@Resource
	private BoardService boardService;
 
	//자바빈 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}	
	//자유게시판  목록
	@RequestMapping("/board/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="boa_cate",defaultValue="") String boa_cate) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boa_cate", boa_cate);
		
		//총 게시글 수
		int count = boardService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
			log.debug("<<pageNum>> : " + currentPage);
		}

		//페이징 처리
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"list.do","&boa_cate="+boa_cate);
		
		//목록호출
		List<BoardVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());

			list = boardService.selectList(map);
		}

		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}

		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("boardList");
		//데이터 저장
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());

		return mav;  
	}
	
	//자유게시판 게시글 쓰기
	@RequestMapping(value="/board/boardWrite.do", method=RequestMethod.GET)
	public String Form()
	{
		return "boardWrite";
	}
	//글쓰기 처리
	@RequestMapping(value="/board/boardWrite.do", method=RequestMethod.POST)
	public String Submit(@Valid BoardVO boardVO, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		// 유효성 오류가 있는 경우
		if(result.hasErrors()) {
			return "boardWrite";
		}
		// 정보 셋팅
		Integer mem_num = (Integer)session.getAttribute("user_num");
		boardVO.setMem_num(mem_num);
		// 글쓰기
		boardService.insertBoard(boardVO);

		return "redirect:/board/list.do";
	}

	//====게시판 글 상세======//
	@RequestMapping("/board/boardDetail.do")
	public ModelAndView boardDetail(@RequestParam int boa_num,
			                       HttpSession session) {

		//조회수 증가
		boardService.updateHit(boa_num);
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		BoardVO board = boardService.selectBoard(boa_num);

		//회원 전용 게시글
		if(user_num == null && board.getBoa_mode() == 1) {
			return new ModelAndView("boardModeError");
		}
		
		//차단 된 게시글
		if(board.getBoa_status() == 2) {
			return new ModelAndView("boardModeError","boa_status",board.getBoa_status());
		}		
		
		//HTML 태그 불허
		board.setBoa_title(StringUtil.useNoHtml(board.getBoa_title()));
		//HTML 태그 불허 및 줄바꿈 처리
		board.setBoa_content(board.getBoa_content());

		return new ModelAndView("boardDetail","board",board);

	}

	//이미지 출력
	@RequestMapping("/board/imageView.do")
	public ModelAndView viewImage(@RequestParam int board_num) {
		BoardVO board = boardService.selectBoard(board_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",board.getBoa_uploadfile());
		mav.addObject("filename", board.getBoa_filename());

		return mav;
	}
	
	/* 다음 기약
	
	//동영상 출력 
	@RequestMapping("/board/VideoView.do")
	public ModelAndView viewVideo(@RequestParam int board_num) {
		BoardVO board = boardService.selectBoard(board_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("videoView");
		mav.addObject("VideoFile",board.getBoa_uploadfile());
		mav.addObject("filename", board.getBoa_filename());

		return mav;
	}
	*/
	
	//=====게시판 글 수정======//
	//수정 폼
	@RequestMapping(value="/board/boardModify.do", method=RequestMethod.GET)
	public String formUpdate(@RequestParam int boa_num, Model model) {
		BoardVO boardVO = boardService.selectBoard(boa_num);
		model.addAttribute("boardVO", boardVO);

		return "boardModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/board/boardModify.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid BoardVO boardVO,
			BindingResult result,
			HttpServletRequest request) {
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardModify";
		}

		//글 수정
		boardService.updateBoard(boardVO);

		return "redirect:/board/list.do";
	}

	
	//======게시판 글 삭제========//
	@RequestMapping("/board/delete.do")
	public String submitDelete(@RequestParam int boa_num) {
		//글 삭제
		boardService.deleteBoard(boa_num);

		return "redirect:/board/list.do";
	}
	
	
	
	//CKEditor 이미지 업로드
	@RequestMapping("/board/imageUploader.do")
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile upload, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		// 업로드할 폴더 경로
		String realFolder = session.getServletContext().getRealPath("/resources/image_upload");

		// 업로드할 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + org_filename;

		System.out.println("원본 파일명 : " + org_filename);
		System.out.println("저장할 파일명 : " + str_filename);

		String filepath = realFolder + "\\" + str_filename;
		System.out.println("파일경로 : " + filepath);

		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}
		upload.transferTo(f);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath()+"/resources/image_upload/"+str_filename);

		return map;
	}
	
	/* 다음 기약
	  
	  //CKEditor 동영상 업로드
	@RequestMapping("/board/VideoUploader.do")
	@ResponseBody
	public Map<String,Object> uploadvideo(MultipartFile upload, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		// 업로드할 폴더 경로
		String realFolder = session.getServletContext().getRealPath("/resources/image_upload");

		// 업로드할 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + org_filename;

		System.out.println("원본 파일명 : " + org_filename);
		System.out.println("저장할 파일명 : " + str_filename);

		String filepath = realFolder + "\\" + str_filename;
		System.out.println("파일경로 : " + filepath);

		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}
		upload.transferTo(f);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath()+"/resources/image_upload/"+str_filename);

		return map;
	}*/
	

	
	
}