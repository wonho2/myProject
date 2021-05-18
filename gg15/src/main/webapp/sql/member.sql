create table member(
	mem_num number not null,
	mem_id varchar2(20) unique not null,
	mem_auth number(1) default 2 not null,
	constraint member_pk primary key (mem_num)
);

create table member_detail(

);