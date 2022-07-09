package kr.dstar.mab.service;

import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookDto;
import kr.dstar.mab.dto.BookUpdateDto;
import kr.dstar.mab.enumeration.BookStatus;
import kr.dstar.mab.exception.book.BookNotFoundException;
import kr.dstar.mab.mapper.BookMapper;
import kr.dstar.mab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper mapper;

    public void createBook(BookCreateDto bookCreateDto) {
        Book book = mapper.createDtoToEntity(bookCreateDto);

        bookRepository.save(book);
    }

    public void updateBook(Long id, BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(bookUpdateDto.getTitle());
        book.setStatus(bookUpdateDto.getStatus());
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setStatus(BookStatus.DELETED);
    }

    public BookDto getBook(Long id) {
        return mapper.entityToDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

}
