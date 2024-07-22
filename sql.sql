
--@block
use chatapplication;

--@block
CREATE TABLE user(
    userId int primary key auto_increment,
    userName varchar(255),
    password varchar(255)
);

--@block
SELECT * FROM user;

--@block
INSERT INTO user (userName, password) values("rishav das", "123");

--@block
delete from user 
where userId = 3;