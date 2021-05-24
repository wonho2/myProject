package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	//글쓰기
	@Insert("INSERT INTO board (boa_num,boa_cate,boa_title,boa_uploadfile,boa_content,boa_mode,mem_num) "
		+ "VALUES (board_seq.nextval,#{boa_cate},#{title},#{upload},#{content},#{bod_mode},#{mem_num})")
	public void insertBoard(BoardVO board);

	//총 레코드 수
	public int selectRowCount();
	
	//글 목록
	public List<BoardVO> selectList(Map<String,Object> map);	
	
	//조회수 증가
	//@Update("UPDATE board SET boa_hit=boa_hit+1 WHERE boa_num=#{boa_num}")
	//public void updateHit(Integer board_num);

}


