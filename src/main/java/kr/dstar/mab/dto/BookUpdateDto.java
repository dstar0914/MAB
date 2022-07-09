package kr.dstar.mab.dto;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.Data;

@Data
public class BookUpdateDto {

    private String title;

    private BookStatus status;

}
