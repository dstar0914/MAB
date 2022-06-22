package kr.dstar.mab.service;

import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreate;
import kr.dstar.mab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {

    private final BookRepository bookRepository;

    public void createBook(BookCreate bookCreate) {
        // TODO: 2022/06/23 mapper 작업.
        Book book = new Book();
        book.setTitle(bookCreate.getTitle());

        bookRepository.save(book);
    }

}
