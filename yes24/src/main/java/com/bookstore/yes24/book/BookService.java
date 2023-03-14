package com.bookstore.yes24.book;

import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    public Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
    }

    public Book findBookTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle).orElseThrow(IllegalArgumentException::new); //
    }

    public Page<Book> findBookList(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page,size,
                Sort.by("bookId").descending()));
    }

    public List<BookResponseDto> findAuthorBookList(String author, int page, int size) {
        return bookRepository.findByAuthor(author, PageRequest.of(page, size, Sort.by("bookId").descending())).toList()
                .stream().map(BookResponseDto::of).collect(Collectors.toList());
    }

    @Transactional
    public BookResponseDto creatBook(BookCreateDto dto) {
        return BookResponseDto.of(
                bookRepository.save(Book.of(
                        dto.getTitle(),
                        dto.getAuthor(),
                        dto.getPrice(),
                        dto.getQuantity()
                )));
    }

    @Transactional
    public BookResponseDto updateBook(BookUpdateDto dto) {
        Book findBook = bookRepository.findById(dto.getBookId()).orElseThrow(IllegalArgumentException::new);
        findBook.update(dto);

        return BookResponseDto.of(findBook);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
