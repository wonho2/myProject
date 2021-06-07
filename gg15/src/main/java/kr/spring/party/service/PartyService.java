package kr.spring.party.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import kr.spring.party.dao.PartyMapper;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyReplyVO;
import kr.spring.party.vo.PartyVO;

@Service("partyService")
public class PartyService {
	
	@Resource
	private PartyMapper partyDAO;
	
	//글쓰기
	public void insertParty(PartyVO vo) {
		partyDAO.insertParty(vo);
	}
	
	//총 파티 게시판 게시글 수
	public int selectPartyCount() {
		return partyDAO.selectPartyCount();
	}
	
	//파티 게시물 리스트 불러오기
	public List<PartyVO> selectPartyList(Map<String, Object> map) {
		return partyDAO.selectPartyList(map);
    }
	
	//파티 게시물 상세 페이지
	public PartyVO selectParty(int boardNum) {
		return partyDAO.selectParty(boardNum);
	}
	
	//파티 해당 게시 글의 조회수 증가
	public void updateHit(int boardNum) {
		partyDAO.updateHit(boardNum);
	}
	
	//게시물 수정
	public void updateParty(PartyVO vo) {
		partyDAO.updateParty(vo);
	}
	
	//게시물 삭제
	public void deleteParty(int par_num) {
		//게시글 추천 먼저 삭제
		partyDAO.deleteFavByParNum(par_num);
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		partyDAO.deletePartyReplyByBoardNum(par_num);
		partyDAO.deleteParty(par_num);
	}
	
	//==========댓글 시작================//
	public List<PartyReplyVO> selectPartyListReply(Map<String, Object> map) {
		return partyDAO.selectPartyListReply(map);
	}

	public int selectPartyRowCountReply(Map<String, Object> map) {
		return partyDAO.selectPartyRowCountReply(map);
	}

	public void insertPartyReply(PartyReplyVO partyReply) {
		partyDAO.insertPartyReply(partyReply);
	}

	public void updatePartyReply(PartyReplyVO partyReply) {
		partyDAO.updatePartyReply(partyReply);
	}

	public void deletePartyReply(Integer pop_num) {
		partyDAO.deletePartyReply(pop_num);
	}
  
	//==========댓글 끝=================//
	//==========게시글 추천 ==============//
	public PartyFavVO selectFav(PartyFavVO fav) {
		return partyDAO.selectFav(fav);
	}
	public int selectFavCount(Integer pop_num) {
		return partyDAO.selectFavCount(pop_num);
	}
	public void insertFav(PartyFavVO fav) {
		partyDAO.insertFav(fav);
	}
	public void deleteFav(Integer fav_num) {
		partyDAO.deleteFav(fav_num);
	}
}	
