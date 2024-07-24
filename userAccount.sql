--@block
use chatapplication;

--@block
CREATE TABLE user_account (
    userId INT PRIMARY KEY,
    userName VARCHAR(255),
    gender CHAR NOT NULL DEFAULT '',
    image LONGBLOB,
    imageString VARCHAR(255),
    status CHAR(1) NOT NULL DEFAULT '1',
    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES user(userId) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
)ENGINE = InnoDB;


--@block
ALTER TABLE user_account
ALTER COLUMN imageString SET DEFAULT '';

--@block
SELECT * FROM user_account;

--@block
DELETE FROM user_account 
where userName in ("Rishav Das", "lol", "mini", "user3");