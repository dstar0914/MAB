package kr.dstar.mab.controller;

import kr.dstar.mab.dto.BookCreate;
import kr.dstar.mab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public void createBook(@Validated @RequestBody BookCreate bookCreate) {
        bookService.createBook(bookCreate);
    }

}
