/*
 *  포지션게시판 : 게시물
 */
CREATE TABLE position
(
    pos_num NUMBER NOT NULL, /*게시물 고유번호 <primary>*/
    pos_type NUMBER NOT NULL, /*종류 : 탑(1), 정글(2), 미드(3), 원딜(4), 서폿(5), 공지(0)*/
    mem_num NUMBER NOT NULL, /*작성자(회원) 고유번호 <foreign : member>*/
    pos_title VARCHAR2(50) NOT NULL, /*제목*/
    pos_content CLOB NOT NULL, /*내용*/
    pos_uploadfile BLOB, /*업로드 파일*/
    pos_date DATE NOT NULL, /*작성일*/
    pos_view NUMBER DEFAULT 0 NOT NULL, /*조회수*/
    /*댓글수 : pos_reply NUMBER DEFAULT 0 NOT NULL AUTO_INCREMENT ?*/
    /*추천수 : pos_fav NUMBER DEFAULT 0 NOT NULL AUTO_INCREMENT ?*/
    CONSTRAINT position_pk PRIMARY KEY(pos_num),
    CONSTRAINT position_fk FOREIGN KEY(mem_num) REFERENCES member(mem_num)
);

/*
 * 포지션게시판 : 댓글
 */
CREATE TABLE position_reply
(
    por_num NUMBER NOT NULL, /*댓글 고유번호 <primary>*/
    pos_num NUMBER NOT NULL, /*해당 댓글이 소속된 게시물 고유번호 <foreign : position>*/
    mem_num NUMBER NOT NULL, /*댓글 작성자(회원) 고유번호 <foreign : member>*/
    por_content VARCHAR2(400) NOT NULL, /*내용*/
    por_date DATE NOT NULL, /*작성일*/
    /*추천수 : por_fav NUMBER DEFAULT 0 NOT NULL AUTO_INCREMENT ?*/
    CONSTRAINT position_reply_pk PRIMARY KEY(por_num),
    CONSTRAINT position_reply_fk_boardNum FOREIGN KEY(pos_num) REFERENCES position(pos_num),
    CONSTRAINT position_reply_fk_memNum FOREIGN KEY(mem_num) REFERENCES member(mem_num)
);

/*
 * 포지션게시판 : 게시물 추천
 */
CREATE TABLE position_fav
(
    pof_num NUMBER NOT NULL, /*추천 고유번호 <primary>*/
    pos_num NUMBER NOT NULL, /*게시물 고유번호 <foreign : position>*/
    mem_num NUMBER, /*추천한 회원 고유번호 <foreign : member>*/
    CONSTRAINT position_fav_pk PRIMARY KEY(pof_num),
    CONSTRAINT position_fav_fk_boardNum FOREIGN KEY(pos_num) REFERENCES position(pos_num),
    CONSTRAINT position_fav_fk_memNum FOREIGN KEY(mem_num) REFERENCES member(mem_num)
);

/*
 * 포지션게시판 : 댓글 추천
 */
CREATE TABLE position_rfav
(
    porf_num NUMBER NOT NULL, /*추천 고유번호 <primary>*/
    por_num NUMBER NOT NULL, /*댓글 고유번호 <foreign : position_reply>*/
    mem_num NUMBER NOT NULL, /*추천한 회원 고유번호 <foreign : member>*/
    CONSTRAINT position_rfav_pk PRIMARY KEY(porf_num),
    CONSTRAINT position_rfav_fk_replyNum FOREIGN KEY(por_num) REFERENCES position_reply(por_num),
    CONSTRAINT position_rfav_fk_memNum FOREIGN KEY(mem_num) REFERENCES member(mem_num)
);

/*
 * 시퀀스 모음
 */
CREATE SEQUENCE position_seq;
CREATE SEQUENCE position_reply_seq;
CREATE SEQUENCE position_fav_seq;
CREATE SEQUENCE position_rfav_seq;

/* 커밋 외않됨*/