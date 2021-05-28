package kr.spring.news.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.news.dao.NewsMapper;
import kr.spring.news.vo.NewsVO;
import kr.spring.news.vo.NewsReplyVO;

@Service("newsService")
public class NewsService {
	//의존 관계 설정
	@Resource
	private NewsMapper newsMapper;
	
	//글쓰기
	public void insertNews(NewsVO news) {
		newsMapper.insertNews(news);
	}
	
	//총 레코드 수
	public int selectNewsCount() {
		return newsMapper.selectNewsCount();
	}
	
	//글 목록
	public List<NewsVO> selectList(Map<String,Object> map){
		return newsMapper.selectList(map);
	}
	
	//글 상세 페이지
	public NewsVO selectNews(Integer new_num) {
		return newsMapper.selectNews(new_num);
	}
	
	//조회수 증가
	public void updateHit(Integer new_num) {
		newsMapper.updateHit(new_num);
	}
	
	//글 수정
	public void updateNews(NewsVO news) {
		newsMapper.updateNews(news);
	}
	
	//게시물 삭제
		public void deleteNews(Integer new_num) {
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		newsMapper.deleteReplyByBoardNum(new_num);
	    newsMapper.deleteNews(new_num);
		   } 
		   
	
	//댓글
	public List<NewsReplyVO> selectListReply(Map<String, Object> map) {
		return newsMapper.selectListReply(map);
	}

	public int selectRowCountReply(Map<String, Object> map) {
		return newsMapper.selectRowCountReply(map);
	}

	public void insertReply(NewsReplyVO newsReply) {
		newsMapper.insertReply(newsReply);
	}

	public void updateReply(NewsReplyVO newsReply) {
		newsMapper.updateReply(newsReply);
	}

	public void deleteReply(Integer ner_num) {
		//(*******주의)댓글 좋아요가 있을 경우
		//newsMapper.deleteReFavByRe_num(ner_num);
		newsMapper.deleteReply(ner_num);
	}
	
}
