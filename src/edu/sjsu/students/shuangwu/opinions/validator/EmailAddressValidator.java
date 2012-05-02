package edu.sjsu.students.shuangwu.opinions.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmailAddressValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailAddressValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public boolean supports(Class clazz) {
		return clazz.isAssignableFrom(String.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		String s = (String) obj;
		Matcher matcher = pattern.matcher(s);
		if (!matcher.matches()) {
			errors.rejectValue(null, "user.email.invalid",
					"Please fill a valid email");
		}
	}

}
