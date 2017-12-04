-- create database 'java';
-- use 'java';

create table 'student' (
    'id'            varchar(15) null,   --학번
    'pw'            varchar(15) null,   --비밀번호
    'name'          varchar(15) null,   --이름
    'birth'         varchar(15) null,   --생일
    'enter'         varchar(15) null,   --입학일
    'college'       varchar(15) null,   --대학
    'major'         varchar(15) null,   --전공
    'grade'         varchar(15) null,   --학년
    'status'        varchar(15) null,    --휴복학 상태
    'isAdmin'       varchar(6) 'false'
);

CREATE TABLE 'lecture' (
    'number'            varchar(15) null,   --수업번호
    'class'             varchar(15) null,   --분반
    'name'              varchar(15) null,   --이름
    'classify'          varchar(15) null,   --구분
    'grade'             varchar(15) null,   --학년
    'credit'            varchar(15) null,   --학점
    'credit_theory'     varchar(15) null,   --이론
    'credit_practice'   varchar(15) null,   --실습
    'lectureTime'       varchar(15) null,   --시간
    'lecture_room'      varchar(15) null,   --강의실
    'maxStudent'        int null,           --수강인원
    'prof'              varchar(15) null,   --교수
    'language'          varchar(15) null,   --언어
    'year'              varchar(15) null    --년도/학기
);

CREATE TABLE 'interest' (
    'id'            varchar(15) null,   --학번
    'lecture'       varchar(15) null,   --수업번호
    'class'         varchar(15) null    --분반
);

CREATE TABLE '2017/2' (
    'id'            varchar(15) null,   --학번
    'lecture'       varchar(15) null,   --수업번호
    'class'         varchar(15) null,   --분반
    'midterm'       varchar(15) null,   --중간고사성적
    'final'         varchar(15) null,   --기말성적
    'score'         varchar(15) null,   --학점
    'semester'      varchar(15) null    --학기
);

CREATE TABLE '2017/1' (
    'id'            varchar(15) null,   --학번
    'lecture'       varchar(15) null,   --수업번호
    'class'         varchar(15) null,   --분반
    'midterm'       varchar(15) null,   --중간고사성적
    'final'         varchar(15) null,   --기말성적
    'score'         varchar(15) null,   --학점
    'semester'      varchar(15) null    --학기
);

insert into 'student' values (
    '1501117',
    '960410',
    '방일섭',
    '1996.04.10',
    '2015.03.05',
    '전자정보공학대학',
    '정보보호학과',
    '3',
    '0',
    'false'
);

insert into 'student' values (
    '1',
    '1',
    'tester',
    '0000.00.00',
    '0000.00.00',
    '전자정보공학대학',
    '컴퓨터공학과',
    '1',
    '0',
    'false'
);

insert into 'student' values (
    'admin',
    'admin',
    'admin',
    '0000.00.00',
    '0000.00.00',
    '관리자',
    '관리자',
    '0',
    '0',
    'true'
);

insert into 'lecture' values (
    '001111',
    '001',
    'JAVA',
    '전공필수',
    '1',
    '3',
    '1',
    '2',
    '화/목 10:30-12:00',
    '율404',
    40,
    '안용학',
    '한국어/KOREAN',
    '2017/2'
);

insert into 'lecture' values (
    '001112',
    '001',
    'TEST',
    '전공필수',
    '1',
    '3',
    '1',
    '2',
    '화/목 10:30-12:00',
    '율404',
    40,
    '안용학',
    '한국어/KOREAN',
    '2017/2'
);

insert into 'lecture' values (
    '001110',
    '001',
    'TEST',
    '전공필수',
    '1',
    '3',
    '1',
    '2',
    '화/목 10:30-12:00',
    '율404',
    40,
    '안용학',
    '한국어/KOREAN',
    '2017/1'
);


insert into '2017/2' values (
    '15011107',
    '001111',
    '001',
    '35',
    '',
    '',
    '5'
);

insert into '2017/2' values (
    '15011107',
    '001112',
    '001',
    '45',
    '',
    '',
    '5'
);

insert into '2017/2' values (
    '1',
    '001111',
    '001',
    '35',
    '',
    '',
    '2'
);

insert into '2017/1' values (
    '1',
    '001110',
    '001',
    '35',
    '45',
    'A',
    '1'
);

insert into '2017/1' values (
    '15011107',
    '001110',
    '001',
    '35',
    '45',
    'A',
    '4'
);

INSERT INTO 'interest' VALUES(
    '15011107',
    '001111',
    '001'
);

INSERT INTO 'interest' VALUES(
    '1',
    '001111',
    '001'
);
