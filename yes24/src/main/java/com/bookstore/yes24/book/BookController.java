package com.bookstore.yes24.book;

import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

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
    public ResponseEntity authorBookList(@PathVariable("author") String author,
                                         @RequestParam int page,
                                         @RequestParam int size) {
        Page<Book> pageBookList = bookService.findAuthorBookList(author,page - 1, size);
        List<Book> BookList = pageBookList.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(bookMapper.BookToBookResponseDtoList(BookList), pageBookList),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody BookCreateDto bookCreateDto) {

        Book book = bookService.creatBook(
                bookMapper.BookcreatDtoToBook(bookCreateDto)
        );

        return new ResponseEntity<>(bookMapper.BookToBookResponseDto(book),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity updateBook(@PathVariable("book-id") Long bookId,
                                     @RequestBody BookUpdateDto bookUpdateDto) {
        bookUpdateDto.setBookId(bookId);

        Book book = bookMapper.BookupdateDtoToBook(bookUpdateDto);

        Book responseBook = bookService.updateBook(book);

        return new ResponseEntity<>(bookMapper.BookToBookResponseDto(responseBook), HttpStatus.OK);
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity deleteBook(@PathVariable("book-id") Long bookId) {

        bookService.deleteBook(bookId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
