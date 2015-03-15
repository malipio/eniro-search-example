package com.github.malipio.eniro.search.domain.validator;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegExpPatternValidator implements ConstraintValidator<RegExpPattern, String>{

	@Override
	public void initialize(RegExpPattern constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			Pattern.compile(value);
			return true;
		} catch(PatternSyntaxException e) {
			return false;
		}
	}

}
