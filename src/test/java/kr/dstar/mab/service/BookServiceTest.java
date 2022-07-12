package kr.dstar.mab.service;

import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookUpdateDto;
import kr.dstar.mab.enumeration.BookStatus;
import kr.dstar.mab.exception.book.BookNotFoundException;
import kr.dstar.mab.mapper.BookMapper;
import kr.dstar.mab.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("local")
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper mapper;

    private static final String DEFAULT_TITLE = "첫 글 입니다.";

    @Test
    public void createBook() {
        BookCreateDto bookCreateDto = BookCreateDto.builder()
                .title(DEFAULT_TITLE)
                .build();

        bookService.createBook(mapper.createDtoToEntity(bookCreateDto), null);
    }

    @Test
    public void updateBook() {
        // Given.
        Book book = Book.builder()
                .title(DEFAULT_TITLE)
                .build();

        bookRepository.saveAndFlush(book);

        // When.
        BookUpdateDto bookUpdateDto = BookUpdateDto.builder()
                .title("첫 글 수정입니다.")
                .build();

        bookService.updateBook(book.getId(), mapper.updateDtoToEntity(bookUpdateDto));

        // Then.
        Book updatedBook = bookService.getBook(book.getId());

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getId()).isEqualTo(book.getId());
        assertThat(updatedBook.getTitle()).isEqualTo(bookUpdateDto.getTitle());
    }

    @Test
    public void deleteBook() {
        // Given.
        Book book = Book.builder()
                .title(DEFAULT_TITLE)
                .build();

        bookRepository.saveAndFlush(book);

        // When.
        bookService.deleteBook(book.getId());

        // Then.
        Book deletedBook = bookService.getBook(book.getId());

        assertThat(deletedBook.getStatus()).isEqualTo(BookStatus.DELETED);
    }

    @Test
    public void getBook() {
        // Given.
        Book book = Book.builder()
                .title(DEFAULT_TITLE)
                .build();

        bookRepository.saveAndFlush(book);

        // When.
        Book findBook = bookService.getBook(book.getId());

        // Then.
        assertThat(findBook).isNotNull();
        assertThat(findBook.getId()).isEqualTo(book.getId());
    }

    @Test
    public void getBook_NonExist() {
        Book book = Book.builder()
                .title(DEFAULT_TITLE)
                .build();

        bookRepository.saveAndFlush(book);

        assertThatThrownBy(() -> bookService.getBook(1L))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("not found");
    }

}