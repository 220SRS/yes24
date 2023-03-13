package com.bookstore.yes24.customValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MultiRegExpValidator implements ConstraintValidator<MultiRegExp, String> {
    @Override
    public void initialize(MultiRegExp constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isKorean = Pattern.matches("^[ㄱ-ㅎ가-힣]{1,17}$", value);
        boolean isEnglish = Pattern.matches("^[a-zA-Z]+(\\s?[a-zA-Z]+){1,2}$", value);
        return isKorean || isEnglish;
    }
}

//@Target({ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = ValidAuthorValidator.class)
//public @interface ValidAuthor {
//    String message() default "작가명은 한글 또는 영어로만 작성되어야 합니다.";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
//
//public class ValidAuthorValidator implements ConstraintValidator<ValidAuthor, String> {
//    @Override
//    public void initialize(ValidAuthor constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        boolean isKorean = Pattern.matches("^[ㄱ-ㅎ가-힣]{1,17}$", value);
//        boolean isEnglish = Pattern.matches("^[a-zA-Z]+(\\s?[a-zA-Z]+){1,2}$", value);
//        return isKorean || isEnglish;
//    }
//}
//
//public class Person {
//    @ValidAuthor
//    @Size(max = 30, message = "작가명은 한 글자 이상이거나 30글자 이하여야 합니다")
//    @NotBlank
//    private String author;
//
//// getters and
