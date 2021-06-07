package kr.spring.party.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyReplyVO;
import kr.spring.party.vo.PartyVO;

public interface PartyMapper {
	
	//총 파티 게시글 목록	
	public List<PartyVO> selectPartyList(Map<String, Object> map);
	
	//총 파티 게시물 수
	@Select("SELECT COUNT(*) FROM party")
	public int selectPartyCount();
	
	//총 파티 게시물 상세 페이지
	@Select("SELECT * FROM party p JOIN member_detail m ON p.mem_num=m.mem_num WHERE p.par_num=#{par_num}")
	public PartyVO selectParty(int par_num);
	
	//글쓰기
	@Insert("INSERT INTO party (par_num,mem_num,par_type,par_title,par_content,par_uploadfile,par_filename,par_date) "
			+ "VALUES (party_seq.nextval,#{mem_num},#{par_type},#{par_title},#{par_content},#{par_uploadfile},#{par_filename},SYSDATE)")
	public void insertParty(PartyVO vo);
	
	//해당 파티 게시물의 조회수 증가
	@Update("UPDATE party SET par_hit = par_hit+1 WHERE par_num = #{par_num}")
	public void updateHit(int par_num);
	
	//게시글 수정
	public void updateParty(PartyVO vo);
	
	//게시글 삭제
	@Delete("DELETE FROM party WHERE par_num=#{par_num}")
	public void deleteParty(int par_num);
	
	//============댓글==============//
	public List<PartyReplyVO> selectPartyListReply(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM party_reply WHERE par_num=#{par_num}")
	public int selectPartyRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO party_reply (pop_num,pop_content,par_num,mem_num) VALUES (party_reply_seq.nextval,#{pop_content},#{par_num},#{mem_num})")
	public void insertPartyReply(PartyReplyVO partyReply);
	@Update("UPDATE party_reply SET pop_content=#{pop_content} WHERE pop_num=#{pop_num}")
	public void updatePartyReply(PartyReplyVO partyReply);
	@Delete("DELETE FROM party_reply WHERE pop_num=#{pop_num}")
	public void deletePartyReply(Integer pop_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM party_reply WHERE par_num=#{par_num}")
	public void deletePartyReplyByBoardNum(Integer par_num);
	  
	//=========게시글 추천=============//
	@Select("SELECT * from party_fav where par_num=#{par_num} and mem_num=#{mem_num}")
	public PartyFavVO selectFav(PartyFavVO fav);
	@Select("SELECT COUNT(*) from party_fav where par_num=#{par_num}")
	public int selectFavCount(Integer par_num);
	@Insert("INSERT INTO party_fav (fav_num,par_num,mem_num) VALUES (party_fav_seq.nextval,#{par_num},#{mem_num})")
	public void insertFav(PartyFavVO fav);
	@Delete("DELETE FROM party_fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM party_fav WHERE par_num=#{par_num}")
	public void deleteFavByParNum(Integer par_num);
}
