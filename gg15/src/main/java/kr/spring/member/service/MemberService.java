package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyVO;

@Service("memberService")
public class MemberService {
	@Resource
	private MemberMapper memberMapper;
	//회원 가입
	public void insertMember(MemberVO member) {
		//회원 번호를 생성해서 자바빈에 저장
		member.setMem_num(memberMapper.selectMem_num());
		//포인트 번호 생성
		member.setPoi_num(memberMapper.selectPoi_num());
		//회원 정보 저장
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
		memberMapper.insertPoint(member);
	}
	//아이디 중복 체크 및 로그인 체크
	public MemberVO selectCheckMember(String id) {
		return memberMapper.selectCheckMember(id);
	}

	//회원 상세 정보
	public MemberVO selectMember(Integer mem_num) {
		return memberMapper.selectMember(mem_num);
	}
	//회원 정보 수정
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
	}
	//비밀번호 수정
	public void updatePassword(MemberVO member) {
		memberMapper.updatePassword(member);
	}		
	//관리자 로그인 auth값
	public void updateAdAuth(Integer mem_num) {
		memberMapper.updateAdAuth(mem_num);
	}
	//멤버 로그인 auth값
	public void updateMemAuth(Integer mem_num) {
		memberMapper.updateMemAuth(mem_num);
	}
	//포인트
	public void updatePoint(Integer mem_num) {
		memberMapper.updatePoint(mem_num);
	}
	//회원 탈퇴
	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMember(mem_num);
		memberMapper.deleteMember_detail(mem_num);
	}
	
	//총 내 파티 게시판 게시글 수
	public int selectMyPartyCount(int mem_num) {
		return memberMapper.selectMyPartyCount(mem_num);
	}

	//내 파티 게시물 리스트 불러오기
	public List<PartyVO> selectMyPartyList(Map<String, Object> map) {
		return memberMapper.selectMyPartyList(map);
	}
}
