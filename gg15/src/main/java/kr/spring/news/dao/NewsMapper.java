package kr.spring.news.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.news.vo.NewsVO;
import kr.spring.news.vo.NewsReplyVO;


public interface NewsMapper {
	//글쓰기
	@Insert("INSERT INTO news(new_num, mem_num, new_title, new_content, new_uploadfile, new_filename)"
			+ " VALUES (news_seq.nextval, #{mem_num}, #{new_title}, #{new_content}, #{new_uploadfile}, #{new_filename})")
	public void insertNews(NewsVO news);
	     
	//총 레코드 수
	@Select("SELECT COUNT(*) FROM news")
	public int selectNewsCount();
	
	//글 목록
	public List<NewsVO> selectList(Map<String,Object> map);
	
	//게시글 상세 페이지
	@Select("SELECT * FROM news n JOIN member m ON n.mem_num=m.mem_num WHERE n.new_num=#{new_num}")
	public NewsVO selectNews(Integer new_num);
	
	//조회수 증가
	@Update("UPDATE news SET new_hit=new_hit+1 WHERE new_num=#{new_num}")
	public void updateHit(Integer new_num);
	           
	//글 수정 
	public void updateNews(NewsVO news);
	  
	//글 삭제
	@Delete("DELETE FROM news WHERE new_num=#{new_num}")
	public void deleteNews(Integer new_num);
	
	//게시글 추천수 증가
	@Update("UPDATE news SET new_fav =new_fav+1 WHERE new_num=#{new_num}")
	public void updateFavUp(Integer new_num);
	   
	//게시물 추천수 감소
	@Update("UPDATE news SET new_fav = new_fav-1 WHERE new_num = #{new_num}")
	public void updateFavDown(Integer new_num);
	
	//=================댓글================//
		public List<NewsReplyVO> selectListReply(Map<String,Object> map);
		@Select("SELECT COUNT(*) FROM news_reply WHERE new_num=#{new_num}")
		public int selectRowCountReply(Map<String,Object> map);
		@Insert("INSERT INTO news_reply (ner_num,ner_content,new_num,mem_num) VALUES (news_reply_seq.nextval,#{ner_content},#{new_num},#{mem_num})")
		public void insertReply(NewsReplyVO newsReply);
		@Update("UPDATE news_reply SET ner_content=#{ner_content} WHERE ner_num=#{ner_num}")
		public void updateReply(NewsReplyVO newsReply);
		@Delete("DELETE FROM news_reply WHERE ner_num=#{ner_num}")
		public void deleteReply(Integer ner_num);
		//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제 
		@Delete("DELETE FROM news_reply WHERE new_num=#{new_num}")
		public void deleteReplyByBoardNum(Integer new_num);

	
}
