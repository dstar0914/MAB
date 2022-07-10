package kr.dstar.mab.dto;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class BookUpdateDto {

    private String title;

    private BookStatus status;

    @Builder
    public BookUpdateDto(String title, BookStatus status) {
        this.title = title;
        this.status = status;
    }

}
