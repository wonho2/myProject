/*공략*/
create table manualtool(
man_num number not null primary key,
mem_num number not null,
man_champion varchar2(20) not null,
man_season varchar2(30) not null,
man_date date default sysdate not null ,
man_update date default sysdate not null,
man_title varchar2(60) not null,
man_content clob not null,
man_uploadfile blob,
man_filename varchar2(300),
man_hit number default 0 not null,
constraint manualtool_fk foreign key(mem_num)
references member (mem_num)
);

create table manualtool_reply(	/* 공략 게시판 댓글 */
    mar_num number not null primary key,	/* 댓글 고유 번호 */
    man_num number not null,	/* 댓글이 소속된 게시물의 고유 번호 */
    mem_num number not null,	/* 댓글 작성자 고유 회원 번호 */
    mar_content varchar2(400) not null,	/* 댓글 내용 */
    mar_date date default sysdate not null,	/* 댓글 작성일 */
	constraint manualtool_reply_fk1 foreign key (mem_num)
	references member (mem_num),
    constraint manualtool_reply_fk2 foreign key (man_num)
    references manualtool (man_num)
);

create table manualtool_fav(	/* 공략 게시판 글 추천 */
    maf_num number not null primary key,	/* 추천 고유 번호 */
    man_num number not null,	/* 추천한 게시물 고유 번호 */
    mem_num number not null,	/* 추천한 회원 고유 번호 */
    constraint manualtool_fav_fk1 foreign key (mem_num)
	references member (mem_num),
    constraint manualtool_fav_fk2 foreign key (man_num)
    references manualtool (man_num)
);

/* 시퀀스 모음 */
create sequence manualtool_seq;
create sequence manualtool_reply_seq;
create sequence manualtool_fav_seq;