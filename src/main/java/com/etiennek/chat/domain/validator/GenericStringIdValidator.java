package com.etiennek.chat.domain.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GenericStringIdValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return String.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field", "field.blank");
  }

}
