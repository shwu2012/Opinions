package edu.sjsu.students.shuangwu.opinions.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.sjsu.students.shuangwu.opinions.domain.User;

public class UserValidator implements Validator {

	private Validator emailAddressValidator;

	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"user.email.required", "Please fill email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"user.name.required", "Please fill name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender",
				"user.gender.required", "Please select gender");
		// validate email
		// errors.pushNestedPath("email");
		// ValidationUtils.invokeValidator(emailAddressValidator,
		// user.getEmail(),
		// errors);
		// errors.popNestedPath();
	}

	public void setEmailAddressValidator(Validator emailAddressValidator) {
		this.emailAddressValidator = emailAddressValidator;
	}

}
