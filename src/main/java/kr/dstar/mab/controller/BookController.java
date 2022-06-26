package kr.dstar.mab.controller;

import kr.dstar.mab.dto.BookCreate;
import kr.dstar.mab.dto.BookUpdate;
import kr.dstar.mab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public void createBook(@Valid @RequestBody BookCreate bookCreate) {
        bookService.createBook(bookCreate);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdate bookUpdate) {
        bookService.updateBook(id, bookUpdate);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/books/{id}")
    public void getBook(@PathVariable Long id) {
        bookService.getBook(id);
    }

}
