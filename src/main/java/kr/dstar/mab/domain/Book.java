package kr.dstar.mab.domain;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.ACTIVE;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
