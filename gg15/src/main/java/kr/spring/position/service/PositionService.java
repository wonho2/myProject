package kr.spring.position.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.position.dao.PositionDAO;
import kr.spring.position.etc.PositionType;
import kr.spring.position.vo.PositionCommentFavVO;
import kr.spring.position.vo.PositionCommentVO;
import kr.spring.position.vo.PositionFavVO;
import kr.spring.position.vo.PositionVO;

@Service("positionService")
public class PositionService
{
	@Resource
	private PositionDAO positionDAO;
	 
/*
 * 게시물
 */
	// 게시물 수
	public int selectBoardCount(String posType)
	{
		return positionDAO.selectBoardCount(posType);
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
	// 댓글 삭제  => 게시물 추천 삭제 => 게시물 삭제
	public void deleteBoard(int pos_num)
	{
		positionDAO.deleteCommentsAll(pos_num);
		positionDAO.deleteFavAll(pos_num);
		positionDAO.deleteBoard(pos_num);
	}
	
	// 해당 글의 조회수 증가
	public void updateView(int boardNum)
	{
		positionDAO.updateView(boardNum);
	}
	
/*
 * 게시물 추천
 */
	// 해당 회원이 해당 게시물을 추천했는지 여부 체크
	public boolean selectClickedFav(int pos_num, int mem_num)
	{
		if(positionDAO.selectClickedFav(pos_num, mem_num) == 0) return false;
		return true;
	}
	
	// 해당 게시물의 추천 수
	public int selectFavCount(int pos_num)
	{
		return positionDAO.selectFavCount(pos_num);
	}
	
	// 해당 게시물 추천
	public void insertFav(PositionFavVO vo)
	{
		positionDAO.insertFav(vo);
	}
	
	// 해당 게시물 추천 취소
	public void deleteFav(PositionFavVO vo)
	{
		positionDAO.deleteFav(vo);
	}
	
/*
 * 게시물 댓글
 */
	//  댓글 개수
	public int selectCommentCount(int pos_num)
	{
		return positionDAO.selectCommentCount(pos_num);
	}
	
	// 댓글 리스트
	public List<PositionCommentVO> selectCommentList(Map<String, Object> map)
	{
		return positionDAO.selectCommentList(map);
	}
	
	//  댓글 작성
	public void insertComment(PositionCommentVO vo)
	{
		positionDAO.insertComment(vo);
	}
	
	//  댓글 수정
	public void modifyComment(PositionCommentVO vo)
	{
		positionDAO.modifyComment(vo);
	}

	// 댓글 삭제
	// 댓글에 달린 추천 삭제 => 댓글 삭제
	public void deleteComment(Integer poc_num)
	{
		positionDAO.deleteCommentFavAll(poc_num);
		positionDAO.deleteComment(poc_num);
	}
	
/*
 * 게시물 댓글 추천
 */
	// 해당 회원이 해당 댓글을 추천했는지 여부 체크
	public boolean selectClickedCommentFav(int poc_num, int mem_num)
	{
		if(positionDAO.selectClickedCommentFav(poc_num, mem_num) == 0) return false;
		return true;
	}
	
	// 해당 댓글의 추천 수
	public int selectCommentFavCount(int poc_num)
	{
		return positionDAO.selectCommentFavCount(poc_num);
	}
	
	// 해당 댓글 추천
	public void insertCommentFav(PositionCommentFavVO vo)
	{
		positionDAO.insertCommentFav(vo);
	}
	
	// 해당 댓글 추천 취소
	public void deleteCommentFav(PositionCommentFavVO vo)
	{
		positionDAO.deleteCommentFav(vo);
	}
}
