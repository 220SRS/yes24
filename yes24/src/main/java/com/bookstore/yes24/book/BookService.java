package com.bookstore.yes24.book;

import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto findBook(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);

        return BookResponseDto.of(book);
    }

    public BookResponseDto findBookTitle(String bookTitle) {

        Book book = bookRepository.findByTitle(bookTitle).orElseThrow(IllegalArgumentException::new);

        return BookResponseDto.of(book);
    }

    public MultiResponseDto<BookResponseDto> findBookList(int page, int size) {

        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(page,size,
                Sort.by("bookId").descending()));

        List<Book> bookList = bookPage.getContent();

        return new MultiResponseDto<>(BookResponseDto.ofList(bookList), bookPage);
    }

    public MultiResponseDto<BookResponseDto> findAuthorBookList(String author, int page, int size) {
        Page<Book> bookPage = bookRepository.findByAuthor(author, PageRequest.of(page,size,
                Sort.by("bookId").descending()));

        List<Book> bookList = bookPage.getContent();

        return new MultiResponseDto<>(BookResponseDto.ofList(bookList), bookPage);
    }


    @Transactional
    public BookResponseDto createBook(BookCreateDto bookCreateDto) {

        Book book = Book.of(bookCreateDto);

        bookRepository.save(book);

        return BookResponseDto.of(book);
    }


    @Transactional
    public BookResponseDto updateBook(BookUpdateDto bookUpdateDto) {

        Book findbook = bookRepository.findById(bookUpdateDto.getBookId()).orElseThrow(IllegalAccessError::new);

        findbook.update(bookUpdateDto);

        return BookResponseDto.of(findbook);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
