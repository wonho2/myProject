--뉴스판 게시물
create table news(
new_num number not null,			--게시글 번호 (PK)
mem_num number not null,			--회원 번호 (FK : member)
new_title varchar2(30) not null,	--게시글 제목
new_content clob not null,			--게시글 내용
new_date date,						--게시글 작성일
new_hit number,						--조회수
new_uploadfile blob, 				--업로드 파일
new_filename varchar2(100),
constraint news_pk primary key (new_num),
 constraint news_fk foreign key (mem_num) 
                  references member (mem_num)
);

create table news_reply(
ner_num number not null,			--댓글 식별 번호 (PK)
new_num number not null,			--게시글 번호 (FK : news)
mem_num number not null,			--회원 번호 (FK : member)
ner_content varchar2(400) not null,	--댓글 내용
ner_date date not null,				--댓글 작성일
constraint news_reply_pk primary key (ner_num),
 constraint news_reply_fk1 foreign key (new_num) 
                  references news (new_num),
 constraint news_reply_fk2 foreign key (mem_num) 
                  references member (mem_num)
);

create table news_fav(
	nef_num number NOT NULL,   -- 추천 고유번호 지정
	mem_num number NOT NULL,    --회원번호
	new_num number,             --뉴스게시판 글번호
	constraint news_fav_pk primary key (nef_num),
	constraint news_fav_fk1 foreign key (mem_num) 
							references member (mem_num),
	constraint news_fav_fk2 foreign key (new_num)
    references news (new_num)
	);
	
CREATE SEQUENCE news_seq;
CREATE SEQUENCE news_reply_seq;
CREATE SEQUENCE news_fav_seq;