package kr.dstar.mab.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookCreateDto {

    private String title;

    @Builder
    public BookCreateDto(String title) {
        this.title = title;
    }

}
