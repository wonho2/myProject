package kr.spring.position.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.position.dao.PositionDAO;
import kr.spring.position.vo.PositionVO;

@Service("positionService")
public class PositionService
{
	@Resource
	private PositionDAO positionDAO;
	
	// 총 게시물 수
	public int selectBoardCount()
	{
		return positionDAO.selectBoardCount();
	}
	
	// 게시물 리스트 가져오기
	public List<PositionVO> selectBoardList(Map<String, Object> map)
	{
		return positionDAO.selectBoardList(map);
	}
	
	// 글쓰기
	public void insertBoard(PositionVO vo)
	{
		positionDAO.insertBoard(vo);
	}
	
	// 게시물 상세 페이지
	public PositionVO selectBoard(int boardNum)
	{
		return positionDAO.selectBoard(boardNum);
	}
	
	// 게시물 수정
	public void updateBoard(PositionVO vo)
	{
		positionDAO.updateBoard(vo);
	}
	
	// 게시물 삭제
	public void deleteBoard(int boardNum)
	{
		positionDAO.deleteBoard(boardNum);
	}
	
	// 해당 글의 조회수 증가
	public void updateView(int boardNum)
	{
		positionDAO.updateView(boardNum);
	}
	
	// 해당 글의 추천수 증가
	public void updateFavUp(int boardNum)
	{
		positionDAO.updateFavUp(boardNum);
	}
	
	// 해당 글의 추천수 감소
	public void updateFavDown(int boardNum)
	{
		positionDAO.updateFavDown(boardNum);
	}
	
	// 해당 글의 댓글수 증가
	public void updateCommentUp(int boardNum)
	{
		positionDAO.updateCommentUp(boardNum);
	}
	
	// 해당 글의 댓글수 감소
	public void updateCommentDown(int boardNum)
	{
		positionDAO.updateCommentDown(boardNum);
	}
}
