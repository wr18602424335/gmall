package com.aryun.gmall.ke.commons.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class DatePatternValidator implements ConstraintValidator<DatePattern, String> {

    private String pattern;

    @Override
    public void initialize(DatePattern datePattern) {
        this.pattern = datePattern.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)){
            return true;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            dateFormat.setLenient(false);
            dateFormat.parse(value);
            return true;
        }catch (ParseException e) {
            return false;
        }

    }


}
