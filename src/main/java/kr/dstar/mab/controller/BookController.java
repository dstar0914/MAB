package kr.dstar.mab.controller;

import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookDto;
import kr.dstar.mab.dto.BookUpdateDto;
import kr.dstar.mab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {
        bookService.createBook(bookCreateDto);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        bookService.updateBook(id, bookUpdateDto);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

}
