package kr.spring.party.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.party.vo.PartyVO;

public interface PartyMapper {
	
	//총 파티 게시글 목록	
	public List<PartyVO> selectBoardList(Map<String, Object> map);
	
	//총 파티 게시물 수
	@Select("SELECT COUNT(*) FROM party")
	public int selectBoardCount();
	
	//총 파티 게시물 상세 페이지
	public PartyVO selectBoard(int boardNum);
	
	//글쓰기
	@Insert("INSERT INTO party (par_num,mem_num,par_type,par_title,par_content,par_uploadfile,par_filename,par_date) "
			+ "VALUES (party_seq.nextval,#{mem_num},#{par_type},#{par_title},#{par_content},#{par_uploadfile},#{par_filename},SYSDATE)")
	public void insertBoard(PartyVO vo);
	
	//해당 파티 게시물의 조회수 증가
	public void updateHit(int boardNum);
	
	//게시글 수정
	public void updateBoard(PartyVO vo);
	
	//게시글 삭제
	public void deleteBoard(int boardNum);
}
