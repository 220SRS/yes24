package com.bookstore.yes24.book;

import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponseDto> findBook(@PathVariable("book-id") Long bookId) {
        BookResponseDto bookResponseDto = bookService.findBook(bookId);

        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/bookTitle/{book-title}")
    public ResponseEntity<BookResponseDto> findBookTitle(@PathVariable("book-title") String bookTitle) {
        BookResponseDto bookResponseDto = bookService.findBookTitle(bookTitle);

        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto<BookResponseDto>> BookList(@RequestParam int page, @RequestParam int size) {

        MultiResponseDto<BookResponseDto> multiResponseDto = bookService.findBookList(page - 1, size);

        return ResponseEntity.ok(multiResponseDto);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<MultiResponseDto<BookResponseDto>> authorBookList(@PathVariable("author") String author,
                                         @RequestParam int page,
                                         @RequestParam int size) {

        MultiResponseDto<BookResponseDto> multiResponseDto = bookService.findAuthorBookList(author, page - 1, size);

        return ResponseEntity.ok(multiResponseDto);
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {
        return ResponseEntity.ok(bookService.createBook(bookCreateDto));
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
