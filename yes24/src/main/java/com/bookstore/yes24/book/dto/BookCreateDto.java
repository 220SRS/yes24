package com.bookstore.yes24.book.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class BookCreateDto {

    @Size(max = 255, message = "제목은 한 글자 이상이거나 255글자 이하여야 합니다")
    @NotBlank
    private String title;

    @Pattern(regexp = "^[가-힣]+(\\s?[가-힣]+)*$", message = "한글로만 작성해야 합니다(외국명도 마찬가지로 한글발음으로 작성해야 합니다)")
    @Size(max = 30, message = "작가명은 한 글자 이상이거나 30글자 이하여야 합니다")
    @NotBlank
    private String author;

    @Positive
    private int price;

    @Positive
    private int quantity;
}
