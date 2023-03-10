package com.bookstore.yes24.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(NullPointerException::new);
    }

    public Book findBookTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle).orElseThrow(NullPointerException::new);
    }

    public Page<Book> findBookList(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page,size,
                Sort.by("bookId").descending()));
    }

    public Page<Book> findAuthorBookList(String author, int page, int size) {
        return bookRepository.findByAuthor(author, PageRequest.of(page,size,
                Sort.by("bookId").descending()));
    }

    public Book creatBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {

        Book findBook = bookRepository.findById(book.getBookId()).orElseThrow(NullPointerException::new);

        Optional.ofNullable(book.getTitle())
                .ifPresent(title -> findBook.setTitle(title));
        Optional.ofNullable(book.getAuthor())
                .ifPresent(author -> findBook.setAuthor(author));
        Optional.ofNullable(book.getPrice())
                .filter(price -> price != 0)
                .ifPresent(price -> findBook.setPrice(price));
        Optional.ofNullable(book.getQuantity())
                .filter(price -> price != 0)
                .ifPresent(quantity -> findBook.setQuantity(quantity));

        return bookRepository.save(findBook);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }


}
