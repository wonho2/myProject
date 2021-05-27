create table manualtool(
	man_num number not null primary key,
    mem_num number not null,
    man_champion varchar2(20) not null,
    man_season varchar2(2) not null,
    man_date date not null,
    man_update date not null,
    man_title varchar2(60) not null,
    man_content clob not null,
    man_uploadfile blob not null,
    man_hit number not null,
    man_comment number default 0 not null,
    man_fav number default 0 not null,
    constraint manualtool_fk foreign key(mem_num)
    references member (mem_num)
);

create table manualtool_reply(
    mar_num number not null primary key,
    man_num number not null,
    mem_num number not null,
    mar_content varchar2(400) not null,
    mar_date date not null,
	constraint manualtool_reply_fk1 foreign key (mem_num)
	references member (mem_num),
    constraint manualtool_reply_fk2 foreign key (man_num)
    references manualtool (man_num)
);

create table manualtool_fav(
    maf_num number not null primary key,
    man_num number not null,
    mem_num number not null,
    constraint manualtool_fav_fk1 foreign key (mem_num)
	references member (mem_num),
    constraint manualtool_fav_fk2 foreign key (man_num)
    references manualtool (man_num)
);

create sequence manualtool_seq;
create sequence manualtool_reply_seq;
create sequence manualtool_fav_seq;