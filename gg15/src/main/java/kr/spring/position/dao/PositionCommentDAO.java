package kr.spring.position.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Insert;

import kr.spring.position.vo.PositionCommentVO;

public interface PositionCommentDAO
{
	// 댓글 쓰기
	@Insert("INSERT INTO position_comment(poc_num, pos_num, mem_num, poc_content) "
			+ "VALUES(position_comment_seq.nextval, #{pos_num}, #{mem_num}, #{poc_content})")
	public void insertComment(PositionCommentVO vo) throws SQLException;
}
