CREATE TABLE board( 
	boa_num number not null, /*게시물 번호*/ 
	mem_num number not null, /*사용자 번호*/
	boa_title VARCHAR2(30) NOT NULL, /*게시물 제목*/
	boa_cate VARCHAR2(30) NOT NULL, /*카테고리*/
	boa_mode NUMBER(1) NOT NULL, /*비밀글 설정  0-일반글 1-비밀글 */
	boa_status NUMBER(1) default 1 NOT NULL,/*글 차단 설정 0-정상 1-차단*/
	boa_report NUMBER(1) default 1 NOT NULL ,  /*신고설정 0-미신고 1- 신고*/
	boa_uploadfile blob, /*업로드 파일*/
	boa_filename VARCHAR2(300),/*업로드파일 이름*/
	boa_content clob NOT NULL, /*게시글 내용*/ 
	boa_date date, /*게시 날짜*/
	boa_hit number  default 0 not null, /*조회수*/
	constraint board_pk primary key(boa_num),
	constraint board_fk foreign key(mem_num) references member(mem_num)
);
  
CREATE TABLE board_reply(
	bor_num number not null,/*댓글 번호*/
	boa_num number not null,/*게시글 번호*/
	mem_num number not null,/*사용자 번호*/
	bor_content varchar2(400) not null,/*댓글 내용*/
	bor_date date default sysdate not null,/*댓글 작성 날짜*/
	constraint board_reply_pk primary key(bor_num),
	constraint board_reply_fk_1 FOREIGN key(boa_num) references board (boa_num),
	constraint board_reply_fk_2 FOREIGN key(mem_num) references member (mem_num)
);


CREATE TABLE boa_fav(
	bof_num number not null,/*좋아요 번호*/
	boa_num number not null,/*게시글 번호*/
	mem_num number,/*사용자 번호*/
	constraint bof_num_pk primary key(bof_num),
	constraint boa_fav_fk_1 FOREIGN key(boa_num) references board (boa_num),
	constraint boa_fav_fk_2 FOREIGN key(mem_num) references member (mem_num)
);

CREATE TABLE boa_report(
	boa_report NUMBER(1) default 1 NOT NULL,  /*신고설정 0-미신고 1- 신고*/
	bop_num number not null,/*신고 번호*/
	boa_num number not null,/*게시글 번호*/
	bop_content varchar2(400), /*신고 내용*/
	bop_date date default sysdate not null,/*신고 날짜*/
	mem_num number,/*사용자 번호*/
	constraint bop_num_pk primary key(bop_num),
	constraint boa_report_fk_1 FOREIGN key(boa_num) references board (boa_num),
	constraint boa_report_fk_3 FOREIGN key(mem_num) references member (mem_num)
	
);

 
create sequence board_seq;
create sequence board_reply_seq;
create sequence board_fav_seq;


create sequence board_report_seq;