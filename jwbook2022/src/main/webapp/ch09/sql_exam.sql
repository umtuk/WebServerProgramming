CREATE TABLE student (
    id INT NOT NULL Primary Key AUTO_INCREMENT,
    username VARCHAR(20),
    univ VARCHAR(40),
    birth DATE,
    email VARCHAR(40)
)

SHOW COLUMNS FROM student;

DROP TABLE student;

INSERT INTO student(username, univ, birth, email) values('김길동','AA대학교','1999-10-21','kim@mail.com');
INSERT INTO student(username, univ, birth, email) values('아무개','BB대학교','2000-2-1','amg@kkk.com');
INSERT INTO student(username, univ, birth, email) values('김사랑','AA대학교','2000-3-15','ksr@kmail.com');
INSERT INTO student(username, univ, birth, email) values('김길동','CC대학교','2001-01-12','mol@bmail.com');
INSERT INTO student(username, univ, birth, email) values('이리와','BB대학교','2000-5-5','lee@nn.com');

SELECT * FROM student;
SELECT username, email FROM student;

SELECT * FROM student WHERE username = '김길동';
SELECT * FROM student WHERE username = '김길동' AND univ = 'AA대학교';
SELECT * FROM student WHERE univ = 'AA대학교' OR univ = 'CC대학교';
SELECT * FROM product WHERE price BETWEEN 1000 AND 2000;

SELECT ABS(-20), CEILING(20.25) as CE, ROUND(20.25), FLOOR(20.25), SQRT(4) from dual;

SELECT NOW(), CURRENT_TIMESTAMP(), DAYNAME(now()), PARSEDATETIME('10-01-2020','MM-dd-yy','GMT') from dual;

SELECT * FROM student WHERE username LIKE '김%'

SELECT * FROM student ORDER BY username DESC;
SELECT * FROM student ORDER BY username ASC, univ DESC;

SELECT DISTINCT username FROM student;
SELECT DISTINCT username, univ  FROM student;

SELECT count(id), SUM(id), AVG(id), MAX(id), MIN(id) FROM student;
