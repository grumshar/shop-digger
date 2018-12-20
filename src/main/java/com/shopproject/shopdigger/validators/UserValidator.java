package com.shopproject.shopdigger.validators;

import com.shopproject.shopdigger.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    private static final String LOGIN_PATTERN = "[a-zA-Z0-9]{5,}";

    private static final String PHONE_PATTERN = "^(?:\\(?\\+?48)?(?:[-\\.\\(\\)\\s]*(\\d)){9}\\)?$";


    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserDto userDto = (UserDto) o;
        if (StringUtils.isEmpty(userDto.getFirstName())) {
            errors.rejectValue("firstName", "NotNull.customerModel.firstName");
        }

        Pattern loginPattern = Pattern.compile(LOGIN_PATTERN,
                Pattern.CASE_INSENSITIVE);
        if (!(loginPattern.matcher(userDto.getLogin()).matches())) {
            errors.rejectValue("login", "customerModel.login.pattern");
        }
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            errors.rejectValue("password", "customerModel.password.pattern");
        }
    }
}
