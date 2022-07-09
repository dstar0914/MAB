package kr.dstar.mab.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookDto;
import kr.dstar.mab.dto.BookUpdateDto;
import kr.dstar.mab.enumeration.BookStatus;
import kr.dstar.mab.repository.BookRepository;
import kr.dstar.mab.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("local")
@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void createBook() throws Exception {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setTitle("첫 글 입니다.");

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateBook() throws Exception {
        // Given.
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

        bookRepository.saveAndFlush(book);

        // When.
        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        bookUpdateDto.setTitle("첫 글 수정입니다.");

        mockMvc.perform(put("/books/{id}", book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookUpdateDto)))
                .andExpect(status().isOk());

        // Then.
        BookDto updatedBook = bookService.getBook(book.getId());

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getId()).isEqualTo(book.getId());
        assertThat(updatedBook.getTitle()).isEqualTo(bookUpdateDto.getTitle());
    }

    @Test
    public void deleteBook() throws Exception {
        // Given.
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

        bookRepository.saveAndFlush(book);

        // When.
        mockMvc.perform(delete("/books/{id}", book.getId()))
                .andExpect(status().isOk());

        // Then.
        BookDto deletedBook = bookService.getBook(book.getId());

        assertThat(deletedBook).isNotNull();
        assertThat(deletedBook.getId()).isEqualTo(book.getId());
        assertThat(deletedBook.getStatus()).isEqualTo(BookStatus.DELETED);
    }

    @Test
    public void getBook() throws Exception {
        // Given.
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

        bookRepository.saveAndFlush(book);

        // When.
        MvcResult result = mockMvc.perform(get("/books/{id}", book.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then.
        String responseString = result.getResponse().getContentAsString();
        Book responseObject = objectMapper.readValue(responseString, Book.class);

        assertThat(responseObject).isNotNull();
        assertThat(responseObject.getId()).isEqualTo(book.getId());
        assertThat(responseObject.getTitle()).isEqualTo(book.getTitle());
        assertThat(responseObject.getStatus()).isEqualTo(BookStatus.ACTIVE);
    }
}