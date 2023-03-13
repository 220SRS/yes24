package com.bookstore.yes24.customValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultiRegExpValidator.class})
public @interface MultiRegExp {
    String message() default "한글 혹은 영어로만 작성해야 합니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
