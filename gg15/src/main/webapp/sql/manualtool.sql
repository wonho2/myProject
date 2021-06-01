create table manualtool(	/* 공략 게시판 */
	man_num number not null primary key,	/* 게시물 고유 번호 */
    mem_num number not null,	/* 게시물 작성자 고유 회원 번호 */
    man_champion varchar2(20) not null,	/* 챔피언 종류 */
    man_season varchar2(30) not null,	/* 시즌 */
    man_date date not null default sysdate,	/* 게시물 작성일 */
    man_update date not null default sysdate,	/* 게시물 최종 갱신일 (수정일) */
    man_title varchar2(60) not null,	/* 게시물 제목 */
    man_content clob not null,	/* 게시물 내용 */
    man_uploadfile blob,	/* 업로드 파일 */
    man_filename varchar2(300), /* 업로드 파일명 */
    man_hit number not null default 0,	/* 게시물 조회수 */
    /*
     *	댓글 수, 추천 수는 manualtool_reply, manualtool_fav 조인
     * 	man_comment number default 0 not null,	/* 댓글 수 */
   	 *	man_fav number default 0 not null,	/* 추천 수 */
     */
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