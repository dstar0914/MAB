package kr.dstar.mab.service;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kr.dstar.mab.domain.Book;
import kr.dstar.mab.enumeration.BookStatus;
import kr.dstar.mab.exception.book.BookNotFoundException;
import kr.dstar.mab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {

    private final BookRepository bookRepository;

    public void createBook(Book book, MultipartFile uploadFile) {
        if (uploadFile != null) {
            try {
                String originFileName = uploadFile.getOriginalFilename();

                // TODO: file name 에 대한 규칙 정하고 수정 필요.
                File file = new File(originFileName);

                uploadFile.transferTo(file);

            } catch (IOException e) {
                // TODO: Exception 보완 필요.
                throw new InternalException("Failed to upload.");
            }
        }

        bookRepository.save(book);
    }

    public void updateBook(Long id, Book updateBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.updateBook(updateBook.getTitle(), updateBook.getStatus());
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.updateStatus(BookStatus.DELETED);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

}
