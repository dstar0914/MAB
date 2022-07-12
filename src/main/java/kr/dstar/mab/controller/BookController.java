package kr.dstar.mab.controller;

import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookDto;
import kr.dstar.mab.dto.BookUpdateDto;
import kr.dstar.mab.mapper.BookMapper;
import kr.dstar.mab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    private final BookMapper mapper;

    @PostMapping("/books")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createBook(BookCreateDto dto, @RequestPart(required = false) MultipartFile file) {
        bookService.createBook(mapper.createDtoToEntity(dto), file);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDto dto) {
        bookService.updateBook(id, mapper.updateDtoToEntity(dto));
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return mapper.entitiesToDtos(bookService.getBooks());
    }

    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return mapper.entityToDto(bookService.getBook(id));
    }

}
