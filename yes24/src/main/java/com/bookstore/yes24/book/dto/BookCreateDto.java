package com.bookstore.yes24.book.dto;

import com.bookstore.yes24.customValid.MultiRegExp;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class BookCreateDto {

    @Size(max = 255, message = "제목은 한 글자 이상이거나 255글자 이하여야 합니다")
    @NotBlank
    private String title;

//    @Pattern.List({
//        @Pattern(regexp = "^[a-zA-Z]+(\\s?[a-zA-Z]+){1,2}$", message = "영어 또는 한글로만 작성해야 합니다"),
//        @Pattern(regexp = "^[ㄱ-ㅎ가-힣]{1,17}$", message = "영어 또는 한글로만 작성해야 합니다")
//    })
    @MultiRegExp
    @Size(max = 30, message = "작가명은 한 글자 이상이거나 30글자 이하여야 합니다")
    @NotBlank
    private String author;

    @Max(value = 200000, message = "20만원 이하여야 합니다")
    @Positive
    private int price;

    @Positive
    private int quantity;
}
