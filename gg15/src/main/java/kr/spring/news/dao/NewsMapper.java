package kr.spring.news.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.news.vo.NewsVO;

public interface NewsMapper {
	//글쓰기
	@Insert("INSERT INTO news (new_num, mem_num, new_title, new_content, new_uploadfile)"
			+ " VALUES news_seq.nextval, #{mem_num}, #{title}, #{content}, #{uploadfile})")
	public void insertNews(NewsVO news);
	
	//총 레코드 수
	public int selectRowCount();
	
	//글 목록
	public List<NewsVO> selectList(Map<String,Object> map);
	
	//게시글 상세 페이지
	@Select("SELECT * FROM news n JOIN member m ON n.mem_num=m.mem_num WHERE n.new_num=#{new_num}")
	public NewsVO selectNews(Integer new_num);
	
	//조회수 증가
	@Update("UPDATE news SET hit=hit+1 WHERE new_num=#{new_num}")
	public void updateHit(Integer new_num);
	
	//글 수정
	public void updateNews(NewsVO news);
	
	//글 삭제
	@Delete("DELETE FROM news WHERE new_num=#new_num}")
	public void deleteNews(Integer new_num);
	
	//게시글 추천
	@Update("UPDATE news SET new_fav =new_fav+1 WHERE new_num=#{new_num}")
	public void updateFav(Integer new_num);
}