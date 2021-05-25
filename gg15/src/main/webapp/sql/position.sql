/*
 *  포지션게시판 : 게시물
 */
CREATE TABLE position
(
    pos_num NUMBER PRIMARY KEY, /*게시물 고유번호 <primary>*/
    pos_type VARCHAR2(10) NOT NULL, /*종류 : 탑, 정글, 미드, 원딜, 서포터*/
    mem_num NUMBER NOT NULL, /*작성자(회원) 고유번호 <foreign : member>*/
    pos_title VARCHAR2(50) NOT NULL, /*제목*/
    pos_content CLOB NOT NULL, /*내용*/
    pos_uploadfile BLOB, /*업로드 파일*/
    pos_filename VARCHAR2(300), /*업로드 파일명*/
    pos_date DATE DEFAULT SYSDATE NOT NULL, /*작성일*/
    pos_view NUMBER DEFAULT 0 NOT NULL, /*조회수*/
   	pos_comment NUMBER DEFAULT 0 NOT NULL, /*댓글수*/
    pos_fav NUMBER DEFAULT 0 NOT NULL, /*추천수*/
    FOREIGN KEY (mem_num) REFERENCES member(mem_num)
);

/*
 * 포지션게시판 : 댓글
 */
CREATE TABLE position_comment
(
    poc_num NUMBER PRIMARY KEY, /*댓글 고유번호 <primary>*/
    pos_num NUMBER NOT NULL, /*해당 댓글이 소속된 게시물 고유번호 <foreign : position>*/
    mem_num NUMBER NOT NULL, /*댓글 작성자(회원) 고유번호 <foreign : member>*/
    poc_content VARCHAR2(400) NOT NULL, /*내용*/
    poc_date DATE DEFAULT SYSDATE NOT NULL, /*작성일*/
    poc_fav NUMBER DEFAULT 0 NOT NULL, /*추천수*/
    FOREIGN KEY (pos_num) REFERENCES position(pos_num),
    FOREIGN KEY (mem_num) REFERENCES member(mem_num)
);

/*
 * 포지션게시판 : 게시물 추천
 */
CREATE TABLE position_fav
(
    pof_num NUMBER PRIMARY KEY, /*추천 고유번호 <primary>*/
    pos_num NUMBER NOT NULL, /*게시물 고유번호 <foreign : position>*/
    mem_num NUMBER NOT NULL, /*추천한 회원 고유번호 <foreign : member>*/
    FOREIGN KEY (pos_num) REFERENCES position(pos_num),
    FOREIGN KEY (mem_num) REFERENCES member(mem_num)
);

/*
 * 포지션게시판 : 댓글 추천
 */
CREATE TABLE position_cfav
(
    pocf_num NUMBER PRIMARY KEY, /*추천 고유번호 <primary>*/
    poc_num NUMBER NOT NULL, /*댓글 고유번호 <foreign : position_comment>*/
    mem_num NUMBER NOT NULL, /*추천한 회원 고유번호 <foreign : member>*/
    FOREIGN KEY (poc_num) REFERENCES position_comment(poc_num),
    FOREIGN KEY (mem_num) REFERENCES member(mem_num)
);

/*
 * 시퀀스 모음
 */
CREATE SEQUENCE position_seq;
CREATE SEQUENCE position_comment_seq;
CREATE SEQUENCE position_fav_seq;
CREATE SEQUENCE position_cfav_seq;

/* 커밋 외않됨*/