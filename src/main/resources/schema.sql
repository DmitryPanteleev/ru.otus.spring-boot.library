DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS GENRE;

CREATE TABLE AUTHOR
(
    id        BIGINT PRIMARY KEY auto_increment ,
    `full_name` VARCHAR(255) unique
);

CREATE TABLE GENRE
(
    id    BIGINT PRIMARY KEY auto_increment,
    `genre` VARCHAR(255) unique
);

CREATE TABLE BOOK
(
    id        BIGINT PRIMARY KEY auto_increment,
    `book_name` VARCHAR(255),
    `author`    BIGINT,
     foreign key (`author`) references AUTHOR (`id`)
);

create TABLE BOOK_GENRE ( `book` BIGINT , `genre` BIGINT ,
 foreign key (`book`) references BOOK (`id`), foreign key (`genre`) references GENRE (`id`))
