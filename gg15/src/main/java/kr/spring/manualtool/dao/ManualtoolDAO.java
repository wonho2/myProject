package kr.spring.manualtool.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.manualtool.vo.ManualtoolVO;
import kr.spring.manualtool.vo.ManualtoolFavVO;
import kr.spring.manualtool.vo.ManualtoolCommentVO;

public interface ManualtoolDAO {
	//글쓰기
	@Insert("INSERT INTO manualtool (man_num, mem_num, man_champion, man_season, man_title, man_content, man_uploadfile, man_filename) "
			+ "VALUES (manualtool_seq.nextval, #{mem_num}, #{man_champion}, #{man_season}, #{man_title}, #{man_content} , #{man_uploadfile}, #{man_filename})")
	public void insertManualtool(ManualtoolVO vo);

	//총 레코드 수
	public int selectManualtoolCount(Map<String, Object> map);

	//글 목록
	public List<ManualtoolVO> selectList(Map<String, Object> map);

	//글 상세 페이지
	@Select("SELECT * FROM manualtool mt JOIN member_detail m ON mt.mem_num=m.mem_num WHERE mt.man_num=#{man_num}")
	public ManualtoolVO selectManualtool(Integer man_num);
	
	//조회수 증가
	@Update("UPDATE manualtool SET man_hit = man_hit+1 WHERE man_num = #{man_num}")
	public void updateHit(Integer man_num);
	
	//해당 게시물을 추천했는지 체크
	@Select("SELECT COUNT(*) FROM man_fav WHERE man_num=#{man_num}, mem_num=#{mem_num}")
	public int selectClickedFav(Integer man_num, Integer mem_num);

	//댓글수 증가 (댓글 작성)
	@Update("UPDATE manualtool SET man_comment = man_comment+1 WHERE man_num = #{man_num}")
	public void updateCommentUp(Integer man_num);

	//댓글수 감소 (댓글 삭제)
	@Update("UPDATE manualtool SET man_comment = man_comment-1 WHERE man_num = #{man_num}")
	public void updateCommentDown(Integer man_num);

	//글 수정
	public void updateManualtool(ManualtoolVO vo);

	//글 삭제
	@Delete("DELETE FROM manualtool WHERE man_num=#{man_num}")
	public void deleteManualtool(Integer man_num);
	
	//=====댓글=====
	public List<ManualtoolCommentVO> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM manualtool_reply WHERE man_num=#{man_num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Insert("INSERT INTO manualtool_reply (mar_num, mar_content, man_num, mem_num) VALUES (manualtool_reply_seq.nextval, #{mar_content}, #{man_num}, #{mem_num})")
	public void insertReply(ManualtoolCommentVO ManualtoolReply);
	@Update("UPDATE manualtool_reply SET mar_content=#{mar_content} WHERE mar_num=#{mar_num}")
	public void updateReply(ManualtoolCommentVO ManualtoolReply);
	@Delete("DELETE FROM manualtool_reply WHERE mar_num=#{mar_num}")
	public void deleteReply(Integer mar_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제 
	@Delete("DELETE FROM manualtool_reply WHERE man_num=#{man_num}")
	public void deleteReplyByBoardNum(Integer man_num);

	//=================게시글 좋아요=================//
	@Select("SELECT * from manualtool_fav where man_num=#{man_num} and mem_num=#{mem_num}")
	public ManualtoolFavVO selectFav(ManualtoolFavVO fav);
	@Select("SELECT COUNT(*) from manualtool_fav where man_num=#{man_num}")
	public int selectFavCount(Integer man_num);
	@Insert("INSERT INTO manualtool_fav (maf_num,man_num,mem_num) VALUES (manualtool_fav_seq.nextval,#{man_num},#{mem_num})")
	public void insertFav(ManualtoolFavVO manualtoolFav);
	@Delete("DELETE FROM manualtool_fav WHERE maf_num=#{maf_num}")
	public void deleteFav(Integer maf_num);
	@Delete("DELETE FROM manualtool_fav WHERE man_num=#{man_num}")
	public void deleteFavByManNum(Integer man_num);

	
}
