create table member(
	mem_num number not null,
	mem_id varchar2(20) unique not null,
	mem_auth number(1) default 2 not null,
	constraint member_pk primary key (mem_num)
);

create table member_detail(
	mem_num number not null,
	mem_name varchar2(15) not null,
	mem_pw varchar2(15) not null,
	mem_nick varchar2(15) not null,
	mem_phone varchar2(15) not null,
	mem_email varchar2(50) not null,
	mem_date date default sysdate not null,
	constraint member_detail_pk primary key (mem_num),
	constraint member_detail_fk foreign key (mem_num) 
	references member (mem_num)
);

create table point(
	poi_num number not null,
	mem_num number not null,
	poi_add number(7),
	poi_minus number(7),
	poi_detail varchar2(90) not null,
	poi_date date default sysdate not null,
	constraint poi_num_pk primary key (poi_num),
	constraint mem_num_fk foreign key (mem_num)
	references member (mem_num)
);