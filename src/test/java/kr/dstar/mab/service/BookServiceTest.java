package kr.dstar.mab.service;

import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreate;
import kr.dstar.mab.dto.BookUpdate;
import kr.dstar.mab.enumeration.BookStatus;
import kr.dstar.mab.exception.book.BookNotFoundException;
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

    @Test
    public void createBook() {
        BookCreate bookCreate = new BookCreate();
        bookCreate.setTitle("첫 글 입니다.");

        bookService.createBook(bookCreate);
    }

    @Test
    public void updateBook() {
        // Given.
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

        bookRepository.saveAndFlush(book);

        // When.
        BookUpdate bookUpdate = new BookUpdate();
        bookUpdate.setTitle("첫 글 수정입니다.");

        bookService.updateBook(book.getId(), bookUpdate);

        // Then.
        Book updatedBook = bookService.getBook(book.getId());

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getId()).isEqualTo(book.getId());
        assertThat(updatedBook.getTitle()).isEqualTo(bookUpdate.getTitle());
    }

    @Test
    public void deleteBook() {
        // Given.
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

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
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

        bookRepository.saveAndFlush(book);

        // When.
        Book findBook = bookService.getBook(book.getId());

        // Then.
        assertThat(findBook).isNotNull();
        assertThat(findBook.getId()).isEqualTo(book.getId());
    }

    @Test
    public void getBook_NonExist() {
        Book book = new Book();
        book.setTitle("첫 글 입니다.");

        bookRepository.saveAndFlush(book);

        assertThatThrownBy(() -> bookService.getBook(1L))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("not found");
    }

}