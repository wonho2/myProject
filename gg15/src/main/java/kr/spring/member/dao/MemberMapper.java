package kr.spring.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

public interface MemberMapper {
	//회원관리
	//회원 번호 구하기
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	//회원 등록
	@Insert("INSERT INTO member (mem_num,mem_id) VALUES (#{mem_num},#{mem_id})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO member_detail (mem_num,mem_name,mem_pw,mem_nick,mem_phone,mem_email) VALUES (#{mem_num},#{mem_name},#{mem_pw},#{mem_nick},#{mem_phone},#{mem_email})")
	public void insertMember_detail(MemberVO member);
	//아이디 중복 체크 및 로그인 체크
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.mem_pw FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember(String id);
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
}	
