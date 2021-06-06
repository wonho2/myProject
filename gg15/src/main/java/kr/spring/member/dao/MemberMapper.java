package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyVO;

public interface MemberMapper {
	//회원관리
	//회원 번호 구하기
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	//포인트 번호
	@Select("SELECT point_seq.nextval FROM dual")
	public int selectPoi_num();
	//회원 등록
	@Insert("INSERT INTO member (mem_num,mem_id) VALUES (#{mem_num},#{mem_id})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO member_detail (mem_num,mem_name,mem_pw,mem_nick,mem_phone,mem_email) VALUES (#{mem_num},#{mem_name},#{mem_pw},#{mem_nick},#{mem_phone},#{mem_email})")
	public void insertMember_detail(MemberVO member);
	@Insert("INSERT INTO point (poi_num,mem_num) VALUES (#{poi_num},#{mem_num})")
	public void insertPoint(MemberVO member);
	//아이디 중복 체크 및 로그인 체크
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.mem_pw,p.poi_point FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num LEFT OUTER JOIN point p ON m.mem_num=p.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember(String id);
	//포인트 체크
	@Select("SELECT m.mem_num,p.poi_num FROM member m JOIN point p ON m.mem_num=p.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectPoint(Integer mem_num);
	//회원 상세 정보
	@Select("SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	//회원 정보 수정
	@Update("UPDATE member_detail SET mem_name=#{mem_name},mem_nick=#{mem_nick},mem_phone=#{mem_phone},mem_email=#{mem_email}WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	//비밀번호 수정
	@Update("UPDATE member_detail SET mem_pw=#{mem_pw} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	//관리자 auth값 변경 
	@Update("UPDATE member SET mem_auth=3 WHERE mem_num=#{mem_num}")
	public void updateAdAuth(Integer mem_num);
	//멤버 auth값 변경
	@Update("UPDATE member SET mem_auth=2 WHERE mem_num=#{mem_num}")
	public void updateMemAuth(Integer mem_num);
	//포인트
	@Update("UPDATE point SET poi_point=poi_point+5 WHERE mem_num=#{mem_num}")
	public void updatePoint(Integer mem_num);
	//회원 탈퇴
	@Update("UPDATE member SET mem_auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);	


	//총 내 파티 게시글 목록	
	public List<PartyVO> selectMyPartyList(Map<String, Object> map);

	//총 내 파티 게시물 수
	@Select("SELECT COUNT(*) FROM party")
	public int selectMyPartyCount(int mem_num);
}	
