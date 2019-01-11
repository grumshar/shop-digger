package com.shopproject.shopdigger.validators;

import com.shopproject.shopdigger.dao.UserRepository;
import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;


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
        User user = userRepository.findFirstByLogin(userDto.getLogin());
        if (StringUtils.isEmpty(userDto.getFirstName())) {
            errors.rejectValue("firstName", "NotNull.customerModel.firstName");
        }
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN,
                Pattern.CASE_INSENSITIVE);
        Pattern loginPattern = Pattern.compile(LOGIN_PATTERN,
                Pattern.CASE_INSENSITIVE);
        Pattern phonePattern = Pattern.compile(PHONE_PATTERN,
                Pattern.CASE_INSENSITIVE);
       if (!(loginPattern.matcher(userDto.getLogin()).matches())) {
            errors.rejectValue("login", "customerModel.login.pattern");
        }
        if (user != null) {
            errors.rejectValue("login", "customerModel.login.exist");
        }
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            errors.rejectValue("password", "customerModel.password.pattern");
        }
        if (!(phonePattern.matcher(userDto.getPhoneNumber()).matches())) {
            errors.rejectValue("phoneNumber", "customerModel.phoneNumber.pattern");
        }
    }
}
