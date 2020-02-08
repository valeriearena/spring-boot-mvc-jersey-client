package com.mh.mhcureaccount.util;

import com.mh.mhcureaccount.bean.UserAccount;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserAccountValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return UserAccount.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserAccount userAccount = (UserAccount) target;

        if(StringUtils.isBlank(userAccount.getAccountUserName())){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountUserName", "error.accountUserName", "Account User Name is required.");
        }

        if(StringUtils.isBlank(userAccount.getAccountPassword()) && StringUtils.isBlank(userAccount.getToken())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountPassword", "error.accountPassword", "Account Password OR Token is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "token", "error.token", "Account Password OR Token is required.");
        }

        if(StringUtils.isBlank(userAccount.getNewPassword())){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "error.newPassword", "New Password is required.");
        }

        if(StringUtils.isBlank(userAccount.getConfirmPassword())){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.confirmPassword", "Confirm Password is required.");
        }

    }

    public void validateForgotEmail(Object target, Errors errors) {
        String accountUserName = (String) target;

        if(StringUtils.isBlank(accountUserName)){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountUserName", "error.accountUserName", "Account User Name is required.");
        }
    }


}