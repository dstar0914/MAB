package kr.dstar.mab.dto;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BookDto {

    private Long id;

    private String title;

    private BookStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
