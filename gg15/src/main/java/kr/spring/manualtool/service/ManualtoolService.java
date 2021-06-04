package kr.spring.manualtool.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.manualtool.dao.ManualtoolDAO;
import kr.spring.manualtool.vo.ManualtoolVO;
import kr.spring.manualtool.vo.ManualtoolFavVO;
import kr.spring.manualtool.vo.ManualtoolCommentVO;

@Service("manualtoolService")
public class ManualtoolService {
	//의존 관계 설정
	@Resource
	private ManualtoolDAO manualtoolDAO;
	
	//글 목록
	public List<ManualtoolVO> selectList(Map<String, Object> map){
		return manualtoolDAO.selectList(map);
	}
	
	//글쓰기
	public void insertManualtool(ManualtoolVO vo) {
		manualtoolDAO.insertManualtool(vo);
	}
	
	//총 레코드 수
	public int selectManualtoolCount(Map<String, Object> map) {
		return manualtoolDAO.selectManualtoolCount(map);
	}
	
	//글 상세 페이지
	public ManualtoolVO selectManualtool(Integer man_num) {
		return manualtoolDAO.selectManualtool(man_num);
	}
	
	//조회수 증가
	public void updateHit(Integer man_num) {
		manualtoolDAO.updateHit(man_num);
	}
	
	//=============게시글 좋아요==================//
	public ManualtoolFavVO selectFav(ManualtoolFavVO fav) {
		return manualtoolDAO.selectFav(fav);
	}
	public int selectFavCount(Integer man_num) {
		return manualtoolDAO.selectFavCount(man_num);
	}
	public void insertFav(ManualtoolFavVO manualtoolFav) {
		manualtoolDAO.insertFav(manualtoolFav);
	}
	public void deleteFav(Integer maf_num) {
		manualtoolDAO.deleteFav(maf_num);
	}
	
	/*
	//댓글 개수
	public int selectCommentCount(Integer man_num) {
		return manualtoolDAO.selectCommentCount(man_num);
	}
	*/
	
	//댓글수 증가
	public void updateCommentUp(Integer man_num) {
		manualtoolDAO.updateCommentUp(man_num);
	}
	
	//댓글수 감소
	public void updateCommentDown(Integer man_num) {
		manualtoolDAO.updateCommentDown(man_num);
	}
	
	//글 수정
	public void updateManualtool(ManualtoolVO vo) {
		manualtoolDAO.updateManualtool(vo);
	}
	
	//글 삭제
	public void deleteManualtool(Integer man_num) {
		//게시글 좋아요를 먼저 삭제
		manualtoolDAO.deleteFavByManNum(man_num);
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		manualtoolDAO.deleteReplyByBoardNum(man_num);
		manualtoolDAO.deleteManualtool(man_num);
	}
	
	//=====댓글=====
	public List<ManualtoolCommentVO> selectListReply(Map<String, Object> map) {
		return manualtoolDAO.selectListReply(map);
	}

	public int selectRowCountReply(Map<String, Object> map) {
		return manualtoolDAO.selectRowCountReply(map);
	}

	public void insertReply(ManualtoolCommentVO ManualtoolReply) {
		manualtoolDAO.insertReply(ManualtoolReply);
	}

	public void updateReply(ManualtoolCommentVO ManualtoolReply) {
		manualtoolDAO.updateReply(ManualtoolReply);
	}
	
	public void deleteReply(Integer mar_num) {
		manualtoolDAO.deleteReply(mar_num);
	}
	
}






