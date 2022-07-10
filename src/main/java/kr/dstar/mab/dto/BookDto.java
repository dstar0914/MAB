package kr.dstar.mab.dto;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class BookDto {

    private Long id;

    private String title;

    private BookStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
