CREATE TABLE board( 
boa_num number not null,
mem_num number not null,
boa_title VARCHAR2(30) NOT NULL,
boa_cate VARCHAR2(30) NOT NULL,
boa_mode NUMBER(1) NOT NULL, 
boa_status NUMBER(1) default 1 NOT NULL,
boa_report NUMBER(1) default 1 NOT NULL ,   
boa_uploadfile VARCHAR2(300),
boa_content clob NOT NULL,
boa_date date,
constraint board_pk primary key(boa_num),
constraint board_fk foreign key(mem_num) references member (mem_num)
);
  
CREATE TABLE board_reply(
bor_num number not null,
boa_num number not null,
mem_num number not null,
bor_content varchar2(400) not null,
bor_date date not null,
constraint board_reply_pk primary key(boa_num),
constraint board_reply_fk_1 FOREIGN key(boa_num) references board (boa_num),
constraint board_reply_fk_2 FOREIGN key(mem_num) references member (mem_num)
);


CREATE TABLE boa_fav(
bof_num number not null,
man_num number not null,
mem_num number,
constraint bof_num_pk primary key(bof_num),
constraint boa_fav_fk_1 FOREIGN key(man_num) references board (boa_num),
constraint boa_fav_fk_2 FOREIGN key(mem_num) references member (mem_num)
);