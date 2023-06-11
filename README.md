# ThisisAJAX
-SQL Query
CREATE SEQUENCE BoardSeq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE;
commit;
CREATE SEQUENCE CommentSeq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE;
commit;

CREATE TABLE Board (
  seq NUMBER,
  title VARCHAR2(255),
  content VARCHAR2(4000),
  regdate DATE,
  hit NUMBER,
  PRIMARY KEY (seq)
);


insert into board(seq, title, content, regdate, hit) 
values(1,'월 1억 쉽게 버는 법', '카톡방 문의해주세요', '2023-06-05', 999);
insert into board(seq, title, content, regdate, hit) 
values(2,'이거 누르면 바보', '바보', '2023-06-04', 292);

select * from board;

CREATE TABLE board_comment (
  board_seq NUMBER,
  seq NUMBER,
  content VARCHAR2(4000),
  PRIMARY KEY (board_seq, seq),
  FOREIGN KEY (board_seq) REFERENCES Board (seq)
);
insert into board_comment(board_seq, seq, content) values(1, commentseq.nextval, '여러분 저 저번달에 3억찍음 ㄷㄷ;');

CREATE TABLE KoreaMember (
  id VARCHAR2(50) PRIMARY KEY,
  pwd VARCHAR2(50) NOT NULL,
  name VARCHAR2(50) NOT NULL,
  age NUMBER(3),
  gender VARCHAR2(10),
  email VARCHAR2(100),
  ip VARCHAR2(20)
);

insert into koreamember(id,pwd,name) values('admin','1004','관리자');
commit;



![image](https://github.com/osh8242/ThisisAJAX/assets/127957174/6282dade-1f80-46dd-8fc0-b50469ff14e2)


