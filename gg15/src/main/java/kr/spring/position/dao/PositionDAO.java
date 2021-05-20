package kr.spring.position.dao;

import java.util.List;
import java.util.Map;

import kr.spring.position.vo.PositionVO;

public interface PositionDAO
{
	// 총 게시물 수
	public int selectBoardCount();
	
	// 게시물 목록
	public List<PositionVO> selectBoardList(Map<String, Object> map);
	
	// 글쓰기
	public void insertBoard(PositionVO vo);
	
	// 게시물 상세 페이지
	public PositionVO selectBoard(int boardNum);
	
	// 해당 게시물의 조회수 증가
	public void updateView(int boardNum);
	
	// 게시물 수정
	public void updateBoard(PositionVO vo);
	
	// 게시물 삭제
	public void deleteBoard(int boardNum);
}
