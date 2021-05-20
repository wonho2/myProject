package kr.spring.position.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.spring.position.vo.PositionVO;

@Service("positionService")
public class PositionService
{
	// 총 게시물 수
	public int selectBoardCount()
	{
		return 0;
	}
	
	// 게시물 리스트 가져오기
	public List<PositionVO> selectBoardList(Map<String, Object> map)
	{
		return null;
	}
	
	// 글쓰기
	public void insertBoard(PositionVO vo)
	{
		
	}
	
	// 해당 글의 조회수 증가
	public void updateView(int boardNum)
	{
		
	}
	
	// 게시물 vo 가져오기
	public PositionVO selectBoard(int boardNum)
	{
		return null;
	}
	
	// 게시물 수정
	public void updateBoard(PositionVO vo)
	{
		
	}
	
	// 게시물 삭제
	public void deleteBoard(int boardNum)
	{
		
	}
}
