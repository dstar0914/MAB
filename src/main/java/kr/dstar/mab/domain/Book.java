package kr.dstar.mab.domain;

import kr.dstar.mab.enumeration.BookStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @Builder
    public Book(String title) {
        this.title = title;
        this.status = BookStatus.ACTIVE;
    }

    public Book updateBook(String title, BookStatus status) {
        this.title = title;
        this.status = status;

        return this;
    }

    public Book updateStatus(BookStatus status) {
        this.status = status;

        return this;
    }

}
