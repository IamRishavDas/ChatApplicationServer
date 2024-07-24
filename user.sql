
--@block
use chatapplication;

--@block
CREATE TABLE user(
    userId int primary key auto_increment,
    userName varchar(255),
    password varchar(255)
)ENGINE = InnoDB;

--@block
SELECT * FROM user;

--@block
INSERT INTO user (userName, password) values("rishav das", "123");

--@block
DROP TABLE user;

--@block
DELETE FROM user 
where userName in ("Rishav Das", "lol", "mini", "user3");