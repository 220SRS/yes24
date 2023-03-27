package com.bookstore.yes24.member.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
public class MemberCreateDto {

    @Pattern(regexp = "^[가-힣]+(\\s?[가-힣]+)*$", message = "한글로만 작성해야 합니다(외국명도 마찬가지로 한글발음으로 작성해야 합니다)")
    @Size(max = 30, message = "이름은 한 글자 이상이거나 30글자 이하여야 합니다")
    @NotBlank()
    private String memberName;

    @Size(max = 20, message = "닉네임은 한 글자 이상이거나 20글자 이하여야 합니다")
    @NotBlank()
    private String nickName;

    @NotNull
    private LocalDate birthDate;

    @Email
    @NotBlank()
    private String email;

    @Pattern(regexp = "^01[0-9]-[0-9]{3,4}-[0-9]{4}$", message = "핸드폰번호를 다시 확인해주싶시요")
    @NotBlank()
    private String phone;

}
