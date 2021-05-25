package kr.spring.news.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.news.dao.NewsMapper;
import kr.spring.news.vo.NewsVO;

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
	
	//글 삭제
	public void deleteNews(Integer new_num) {
		newsMapper.deleteNews(new_num);
	}
	
}
