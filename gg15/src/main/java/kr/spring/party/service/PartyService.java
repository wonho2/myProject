package kr.spring.party.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.party.dao.PartyMapper;
import kr.spring.party.vo.PartyVO;

@Service("partyService")
public class PartyService {
	
	@Resource
	private PartyMapper partyDAO;
	
	//글쓰기
	public void insertBoard(PartyVO vo) {
		partyDAO.insertBoard(vo);
	}
	
	//총 파티 게시판 게시글 수
	public int selectBoardCount() {
		return partyDAO.selectBoardCount();
	}
	
	//파티 게시물 리스트 불러오기
	public List<PartyVO> selectBoardList(Map<String, Object> map) {
		return partyDAO.selectBoardList(map);
    }
	
	//파티 게시물 상세 페이지
	public PartyVO selectBoard(int boardNum) {
		return partyDAO.selectBoard(boardNum);
	}
	
	//파티 해당 게시 글의 조회수 증가
	public void updateView(int boardNum) {
		partyDAO.updateHit(boardNum);
	}
	
	//게시물 수정
	public void updateBoard(PartyVO vo) {
		partyDAO.updateBoard(vo);
	}
	
	//게시물 삭제
	/*public void deleteBoard(int boardNum) {
		partyDAO.deleteBoard(boardNum);
	}*/
}	
