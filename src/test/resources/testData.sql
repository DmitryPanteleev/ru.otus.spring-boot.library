insert into GENRE (id, genre) values (1, 'adventure1'),(2, 'adventure2'),(3, 'adventure3'),(4, 'adventure4'),(5, 'adventure5'),(6, 'adventure6');
insert into author(id,  full_name) values (1,  'Ivan1' ),(2,  'Ivan2' ),(3,  'Ivan3' ),(4,  'Ivan4' ),(5,  'Ivan5' ),(6,  'Ivan6' );
insert into BOOK (id, book_name, author) values (1, 'aZbuka1', 1),(2, 'aZbuka2', 2),(3, 'aZbuka3', 3),(4, 'aZbuka4', 4),(5, 'aZbuka5', 5),(6, 'aZbuka6', 6);
insert into comment(id,  comment, book_id) values (1,  'first comment1', 1 ),(2,  'first comment2', 2 ),(3,  'first comment3', 3 ),(4,  'first comment4', 4 ),(5,  'first comment5', 5 ),(6,  'first comment6', 6 );
insert into book_genre(book_id, genre_id) values ( 1, 1),( 2, 1),( 3, 1),( 4, 1),( 5, 1),( 6, 1),
                                                 ( 1, 2),( 2, 2),( 3, 2),( 4, 2),( 5, 2),( 6, 2),
                                                 ( 1, 3),( 2, 3),( 3, 3),( 4, 3),( 5, 3),( 6, 3),
                                                 ( 1, 4),( 2, 4),( 3, 4),( 4, 4),( 5, 4),( 6, 4),
                                                 ( 1, 5),( 2, 5),( 3, 5),( 4, 5),( 5, 5),( 6, 5),
                                                 ( 1, 6),( 2, 6),( 3, 6),( 4, 6),( 5, 6),( 6, 6);
