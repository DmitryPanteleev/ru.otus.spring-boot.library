package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentRepositoryJpa commentRepo;
    private final PermissionService permissionService;

    public BookServiceImpl(BookRepositoryJpa repositoryJpa, AuthorService authorService, GenreService genreService, CommentRepositoryJpa comment, PermissionService permissionService) {
        this.bookRepositoryJpa = repositoryJpa;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentRepo = comment;
        this.permissionService = permissionService;
    }

//    @Override
//    public Book updateBook(Long id, String bookName, String author, List<Genre> genres) {
//        val optionalBook = findById(id);
//        if (optionalBook.isPresent()){
//            optionalBook.get().setBookName(bookName);
//            optionalBook.get().setAuthor(author);
//            optionalBook.get().setBookName(bookName);
//            bookRepositoryJpa.save()
//        }

//        return null;
//    }

    @Override
    public Book updateBook(String id, String bookName, String author, List<Genre> genres) {
        return null;
    }

    @Transactional
    @Override
    public Book create(String bookName, String authorName, List<String> genres) {
        List<Genre> genreList = new ArrayList();
        genres.forEach(s -> genreList.add(genreService.create(s)));
        val book = bookRepositoryJpa.save(new Book(0, bookName, authorService.create(authorName), genreList, null));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        permissionService.addPermissionForUser(book, BasePermission.WRITE, authentication.getName());
        permissionService.addPermissionForUser(book, BasePermission.READ, authentication.getName());
        permissionService.addPermissionForUser(book, BasePermission.DELETE, authentication.getName());
        permissionService.addPermissionForUser(book, BasePermission.ADMINISTRATION, authentication.getName());
        //Если разкомментить то будет доступна и роли юзер
        permissionService.addPermissionForUser(book, BasePermission.READ, "user");
        permissionService.addPermissionForAuthority(book, BasePermission.READ, "ROLE_EDITOR");
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        return bookRepositoryJpa.findById(id);
    }

    @PostFilter("hasPermission(filterObject, 'READ')")
    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Optional<Book>> findByName(String fullName) {
        return bookRepositoryJpa.findBookByBookName(fullName);
    }

    @Transactional
    @Override
    public boolean addComment(String bookName, String newComment) {
        val comment = commentRepo.save(new Comment(0, newComment));
        val bookOption = bookRepositoryJpa.findBookByBookName(bookName);
        if (bookOption.get(0).isPresent()) {
            List<Comment> commentList = bookOption.get(0).get().getComments();
            commentList.add(comment);
            bookOption.get(0).get().setComments(commentList);
            bookRepositoryJpa.saveAndFlush(bookOption.get(0).get());
            return true;
        } else {
            return false;
        }
    }

    //    @PreAuthorize("hasPermission(#id, 'Book', 'UPDATE')")
    //    @PreAuthorize("hasAuthority('admin')")
    @PreAuthorize("hasAnyRole('ROLE_EDITOR')")
    @Transactional
    @Override
    public Book updateBookNameById(long id, String bookName) {
        val book = bookRepositoryJpa.findById(id);
        if (book.isPresent()) {
            book.get().setBookName(bookName);
            return bookRepositoryJpa.save(book.get());
        } else {
            return book.get();
        }
    }

    @PreAuthorize("hasPermission(#book, 'DELETE')")
    @Transactional
    @Override
    public void delete(Book book) {
        bookRepositoryJpa.delete(book);
    }

    @Override
    public List<Comment> getAllComments(long bookId) {
        if (findById(bookId).isPresent()) {
            return findById(bookId).get().getComments();
        } else return List.of();

    }
}
