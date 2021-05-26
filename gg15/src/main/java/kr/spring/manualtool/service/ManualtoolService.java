package kr.spring.manualtool.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.manualtool.dao.ManualtoolDAO;
import kr.spring.manualtool.vo.ManualtoolVO;
import kr.spring.manualtool.vo.ManualtoolCommentVO;

@Service("manualtoolService")
public class ManualtoolService {
	//의존 관계 설정
	@Resource
	private ManualtoolDAO manualtoolDAO;
	
	//글쓰기
	public void insertBoard(ManualtoolVO vo) {
		manualtoolDAO.insertBoard(vo);
	}
	
	//총 레코드 수
	public int selectBoardCount() {
		return manualtoolDAO.selectBoardCount();
	}
	
	//글 목록
	public List<ManualtoolVO> selectBoardList(Map<String, Object> map){
		return manualtoolDAO.selectBoardList(map);
	}
	
	//글 상세 페이지
	public ManualtoolVO selectBoard(int manualtool_num) {
		return manualtoolDAO.selectBoard(manualtool_num);
	}
	
	//조회수 증가
	public void updateHit(int manualtool_num) {
		manualtoolDAO.updateHit(manualtool_num);
	}
	
	//추천수 증가
	public void updateFavUp(int manualtool_num) {
		manualtoolDAO.updateFavUp(manualtool_num);
	}
	
	//추천수 감소
	public void updateFavDown(int manualtool_num) {
		manualtoolDAO.updateFavDown(manualtool_num);
	}
	
	//댓글수 증가
	public void updateCommentUp(int manualtool_num) {
		manualtoolDAO.updateCommentUp(manualtool_num);
	}
	
	//댓글수 감소
	public void updateCommentDown(int manualtool_num) {
		manualtoolDAO.updateCommentDown(manualtool_num);
	}
	
	//글 수정
	public void updateBoard(ManualtoolVO vo) {
		manualtoolDAO.updateBoard(vo);
	}
	
	//글 삭제
	public void deleteBoard(int manualtool_num) {
		manualtoolDAO.deleteBoard(manualtool_num);
	}
	
	//=====댓글=====
	public List<ManualtoolCommentVO> selectListReply(Map<String, Object> map) {
		return manualtoolDAO.selectListReply(map);
	}

	public int selectRowCountReply(Map<String, Object> map) {
		return manualtoolDAO.selectRowCountReply(map);
	}

	public void insertReply(ManualtoolCommentVO boardReply) {
		manualtoolDAO.insertReply(boardReply);
	}

	public void updateReply(ManualtoolCommentVO boardReply) {
		manualtoolDAO.updateReply(boardReply);
	}
	
	public void deleteReply(Integer mar_num) {
		manualtoolDAO.deleteReply(mar_num);
	}
	
}






