drop table if exists author CASCADE ;
drop table if exists book CASCADE ;
drop table if exists book_genre CASCADE ;
drop table if exists comment CASCADE ;
drop table if exists genre CASCADE ;

create table author (
    id bigint auto_increment,
    full_name varchar(255) unique not null,
    primary key (id)
);

create table genre (
    id bigint auto_increment,
    genre varchar(255)unique not null,
    primary key (id)
);

create table book (
    id bigint primary key auto_increment,
    book_name varchar(255) not null,
    author bigint references author(id)
);

create table comment (
    id bigint primary key auto_increment,
    comment varchar(255) not null,
    book_id bigint references book(id) on DELETE CASCADE
);

create table book_genre (
    book_id bigint references book(id) on DELETE CASCADE,
    genre_id bigint references genre (id),
    primary key (book_id, genre_id)
);
