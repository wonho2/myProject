package kr.spring.news.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.news.vo.NewsVO;
import kr.spring.news.vo.NewsFavVO;
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
	@Select("SELECT * FROM news n JOIN member_detail m ON n.mem_num=m.mem_num WHERE n.new_num=#{new_num}")
	public NewsVO selectNews(Integer new_num);
	
	//조회수 증가
	@Update("UPDATE news SET new_hit=new_hit+1 WHERE new_num=#{new_num}")
	public void updateHit(Integer new_num);
	           
	//글 수정 
	public void updateNews(NewsVO news);
	  
	//글 삭제
	@Delete("DELETE FROM news WHERE new_num=#{new_num}")
	public void deleteNews(Integer new_num);
	
	
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
		public void deleteReplyByNewNum(Integer new_num);

		//=========게시글 추천=============//
		@Select("SELECT * from news_fav where new_num=#{new_num} and mem_num=#{mem_num}")
		public NewsFavVO selectFav(NewsFavVO fav);
		@Select("SELECT COUNT(*) from news_fav where new_num=#{new_num}")
		public int selectFavCount(Integer new_num);
		@Insert("INSERT INTO news_fav (nef_num,new_num,mem_num) VALUES (news_fav_seq.nextval,#{new_num},#{mem_num})")
		public void insertFav(NewsFavVO fav);
		@Delete("DELETE FROM news_fav WHERE nef_num=#{nef_num}")
		public void deleteFav(Integer nef_num);
		@Delete("DELETE FROM news_fav WHERE new_num=#{new_num}")
		public void deleteFavByNewNum(Integer new_num);
	
}
