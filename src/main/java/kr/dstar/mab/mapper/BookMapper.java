package kr.dstar.mab.mapper;

import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookDto;
import kr.dstar.mab.dto.BookUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Dto -> Entity.
    Book createDtoToEntity(BookCreateDto dto);

    Book updateDtoToEntity(BookUpdateDto dto);

    // Entity -> Dto.
    BookDto entityToDto(Book book);

    List<BookDto> entitiesToDtos(List<Book> books);

}
