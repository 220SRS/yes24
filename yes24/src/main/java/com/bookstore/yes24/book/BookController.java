package com.bookstore.yes24.book;

import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/{book-id}")
    public ResponseEntity findBook(@PathVariable("book-id") Long bookId) {
        Book findBook = bookService.findBook(bookId);

        return new ResponseEntity<>(bookMapper.BookToBookResponseDto(findBook), HttpStatus.OK);
    }

    @GetMapping("/bookTitle/{book-title}")
    public ResponseEntity findBookTitle(@PathVariable("book-title") String bookTitle) {
        Book findBook = bookService.findBookTitle(bookTitle);

        return new ResponseEntity<>(bookMapper.BookToBookResponseDto(findBook), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity BookList(@RequestParam int page, @RequestParam int size) {
        Page<Book> pageBookList = bookService.findBookList(page - 1, size);
        List<Book> BookList = pageBookList.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(bookMapper.BookToBookResponseDtoList(BookList), pageBookList),
                HttpStatus.OK
        );
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDto>> authorBookList(@PathVariable("author") String author,
                                         @RequestParam int page,
                                         @RequestParam int size) {

        return ResponseEntity.ok(bookService.findAuthorBookList(author, page - 1, size));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {
        return ResponseEntity.ok(bookService.creatBook(bookCreateDto));
    }

    @PatchMapping
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookUpdateDto bookUpdateDto) {
        return ResponseEntity.ok(bookService.updateBook(bookUpdateDto));
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("book-id") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok(null);
    }
}
