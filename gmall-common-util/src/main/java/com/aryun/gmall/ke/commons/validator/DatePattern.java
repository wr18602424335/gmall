package com.aryun.gmall.ke.commons.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
//指定验证器
@Constraint(validatedBy = {DatePatternValidator.class})
public @interface DatePattern {

	String message() default "日期格式不正确";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default { };

	String pattern() default "yyyy-MM-dd HH:mm:ss";

	/**
	 * Defines several {@link DatePattern} annotations on the same element.
	 *
	 * @see DatePattern
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		DatePattern[] value();
	}
}
