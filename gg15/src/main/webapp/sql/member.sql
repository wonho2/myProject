/*
 * 회원정보 저장 
 */
create table member(
	mem_num number not null, /*회원 식별번호*/
	mem_id varchar2(20) unique not null, /*회원 아이디*/
	mem_auth number(1) default 2 not null,
	/*
	 * 회원 레벨 / 0:탈퇴회원 / 1:정지회원 / 2:일반회원 / 3:관리자
	 */
	constraint member_pk primary key (mem_num)
);
/*
 * 회원정보 상세
 */
create table member_detail(
	mem_num number not null,/*회원 식별번호*/
	mem_name varchar2(15) not null, /*이름*/
	mem_pw varchar2(15) not null, /*dd*/
	mem_nick varchar2(15) not null, /*닉네임*/
	mem_phone varchar2(15) not null, /*전화번호*/
	mem_email varchar2(50) not null, /*이메일*/
	mem_date date default sysdate not null, /*가입일*/
	constraint member_detail_pk primary key (mem_num),
	constraint member_detail_fk foreign key (mem_num) 
	references member (mem_num)
);
<<<<<<< HEAD
/*
 * 회원 포인트
 */
=======

create sequence member_seq;

>>>>>>> branch 'main' of https://github.com/wonho2/myProject.git
create table point(
	poi_num number not null, /*포인트 식별번호*/
	mem_num number not null, /*회원 식별번호*/
	poi_point number(10) default 0,
	poi_detail varchar2(90) not null, /*적립/차감 사유(글 작성,충전 등)*/
	poi_date date default sysdate not null, /*포인트 적립/사용날짜*/
	constraint poi_num_pk primary key (poi_num),
	constraint mem_num_fk foreign key (mem_num)
	references member (mem_num)
);
