package kr.spring.party.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.party.vo.PartyVO;

public interface PartyMapper {
	
	//총 파티 게시글 목록	
	public List<PartyVO> selectBoardList(Map<String, Object> map);
	
	//총 파티 게시물 수
	@Select("SELECT COUNT(*) FROM party")
	public int selectBoardCount();
	
	//총 파티 게시물 상세 페이지
	@Select("SELECT * FROM party p JOIN member m ON p.mem_num=m.mem_num WHERE p.par_num=#{par_num}")
	public PartyVO selectBoard(int par_num);
	
	//글쓰기
	@Insert("INSERT INTO party (par_num,mem_num,par_type,par_title,par_content,par_uploadfile,par_filename,par_date) "
			+ "VALUES (party_seq.nextval,#{mem_num},#{par_type},#{par_title},#{par_content},#{par_uploadfile},#{par_filename},SYSDATE)")
	public void insertBoard(PartyVO vo);
	
	//해당 파티 게시물의 조회수 증가
	@Update("UPDATE party SET par_hit = par_hit+1 WHERE par_num = #{par_num}")
	public void updateHit(int par_num);
	
	//게시글 수정
	public void updateParty(PartyVO vo);
	
	//게시글 삭제
	@Delete("DELETE FROM party WHERE par_num=#{par_num}")
	public void deleteParty(int par_num);
}
