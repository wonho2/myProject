package kr.spring.position.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.position.dao.PositionCommentDAO;
import kr.spring.position.vo.PositionCommentVO;

@Service("positionCommentService")
public class PositionCommentService 
{
	@Resource
	private PositionCommentDAO positionCommentDAO;
	
	// 댓글 쓰기
	public boolean insertComment(PositionCommentVO vo)
	{
		try {
			positionCommentDAO.insertComment(vo);
		}
		catch(SQLException e) {
			return false;
		}
		return true;
	}
}
