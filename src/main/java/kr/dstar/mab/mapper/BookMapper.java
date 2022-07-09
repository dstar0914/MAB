package kr.dstar.mab.mapper;

import kr.dstar.mab.domain.Book;
import kr.dstar.mab.dto.BookCreateDto;
import kr.dstar.mab.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book createDtoToEntity(BookCreateDto dto);

    BookDto entityToDto(Book book);

}
