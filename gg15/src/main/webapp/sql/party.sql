CREATE TABLE party(
    par_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    par_type NUMBER NOT NULL,
    par_title VARCHAR2(40) NOT NULL,
    par_content clob NOT NULL,
    par_date date NOT NULL,
    par_uploadfile BLOB,
    par_hit NUMBER(8)  default 0 NOT NULL,
    constraint party_pk primary key (par_num),
    constraint party_fk foreign key (mem_num)
    references member (mem_num)
);

CREATE TABLE party_reply(
    pop_num NUMBER NOT NULL,
    par_num NUMBER NOT NULL,
    mem_num NUMBER NOT NULL,
    pop_content VARCHAR2(4000) NOT NULL,
    pop_date DATE default SYSDATE NOT NULL,
    pop_update DATE NOT NULL,
    constraint party_reply_pk primary key (pop_num),
    constraint party_reply_fk1 foreign key (par_num)
    references party (par_num),
    constraint party_reply_fk2 foreign key (mem_num)
    references member (mem_num)
);

CREATE SEQUENCE party_seq;
CREATE SEQUENCE party_reply_seq;
CREATE SEQUENCE party_fav_seq;

create sequence member_seq;

SELECT COUNT(*) FROM board b JOIN member m ON b.mem_num = m.mem_num;
  
drop table party_fav;
create table party_fav(
  fav_num number not null,
  fav_date date default sysdate not null,
  par_num number not null,
  mem_num number not null,
  constraint party_fav_pk primary key (fav_num),
  constraint fav_party_fk1 foreign key (par_num) references party (par_num),
  constraint fav_member_fk2 foreign key (mem_num) references member (mem_num)    
);


 