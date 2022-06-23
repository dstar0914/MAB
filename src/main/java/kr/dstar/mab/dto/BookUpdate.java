package kr.dstar.mab.dto;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.Data;

@Data
public class BookUpdate {

    private String title;

    private BookStatus status;

}
