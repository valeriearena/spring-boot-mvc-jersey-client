package com.mh.mhcureaccount.controller;

import com.mh.mhcureaccount.bean.UserAccount;
import com.mh.mhcureaccount.service.MHCureAccountService;
import com.mh.mhcureaccount.util.UserAccountValidator;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MHCureAccountController {

    @Autowired
    private MHCureAccountService mhCureAccountService;

    @Autowired
    private UserAccountValidator userAccountValidator;

    @GetMapping("/createAccount")
    public String createPasswordWithToken(UserAccount userAccount) {

        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createPasswordWithToken(@Valid UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        userAccountValidator.validate(userAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createAccount";
        }

        userAccount = mhCureAccountService.registerController(userAccount);
        if(!StringUtils.isBlank(userAccount.getSuccessMessage())){
            redirectAttributes.addFlashAttribute("successMessage", userAccount.getSuccessMessage());
            return "redirect:/manageAccount?accountUserName="+userAccount.getAccountUserName();
        }
        else{

            String[] errorMessages = userAccount.getErrorMessage().split("\n");
            for(int i = 0; i < errorMessages.length; i++){
                redirectAttributes.addFlashAttribute("errorMessage"+i, errorMessages[i]);
            }
            return "redirect:/createAccount?accountUserName="+userAccount.getAccountUserName()+"&token="+userAccount.getToken();

        }

    }

    @GetMapping("/manageAccount")
    public String createPasswordWithCurrentPassword(UserAccount userAccount) {

        return "manageAccount";
    }

    @PostMapping("/manageAccount")
    public String updateAccount(@Valid UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        userAccountValidator.validate(userAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return "manageAccount";
        }

        userAccount = mhCureAccountService.registerController(userAccount);
        if(!StringUtils.isBlank(userAccount.getSuccessMessage())){
            redirectAttributes.addFlashAttribute("successMessage", userAccount.getSuccessMessage());
            return "redirect:/manageAccount?accountUserName="+userAccount.getAccountUserName()+"&accountPassword="+userAccount.getNewPassword();
        }
        else{

            String[] errorMessages = userAccount.getErrorMessage().split("\n");
            for(int i = 0; i < errorMessages.length; i++){
                redirectAttributes.addFlashAttribute("errorMessage"+i, errorMessages[i]);
            }
            return "redirect:/manageAccount?accountUserName="+userAccount.getAccountUserName()+"&accountPassword="+userAccount.getAccountPassword();

        }

    }

    @GetMapping("/token")
    public String sendNewTokenEmail(@RequestParam String accountUserName, UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        userAccountValidator.validateForgotEmail(accountUserName, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createAccount";
        }

        userAccount = mhCureAccountService.forgotPasswordController(userAccount, accountUserName);
        if(!StringUtils.isBlank(userAccount.getSuccessMessage())){
            redirectAttributes.addFlashAttribute("successMessage", userAccount.getSuccessMessage());
            return "redirect:/createAccount?accountUserName="+userAccount.getAccountUserName();
        }
        else{
            String[] errorMessages = userAccount.getErrorMessage().split("\n");
            for(int i = 0; i < errorMessages.length; i++){
                redirectAttributes.addFlashAttribute("errorMessage"+i, errorMessages[i]);
            }
            return "redirect:/createAccount?accountUserName="+userAccount.getAccountUserName();
        }
    }

    @GetMapping("/forgot")
    public String sendForgotEmail(@RequestParam String accountUserName, UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        userAccountValidator.validateForgotEmail(accountUserName, bindingResult);
        if (bindingResult.hasErrors()) {
            return "manageAccount";
        }

        userAccount = mhCureAccountService.forgotPasswordController(userAccount, accountUserName);
        if(!StringUtils.isBlank(userAccount.getSuccessMessage())){
            redirectAttributes.addFlashAttribute("successMessage", userAccount.getSuccessMessage());
            return "redirect:/manageAccount?accountUserName="+userAccount.getAccountUserName();
        }
        else{
            String[] errorMessages = userAccount.getErrorMessage().split("\n");
            for(int i = 0; i < errorMessages.length; i++){
                redirectAttributes.addFlashAttribute("errorMessage"+i, errorMessages[i]);
            }
            return "redirect:/manageAccount?accountUserName="+userAccount.getAccountUserName();
        }
    }

    public MHCureAccountService getMhCureAccountService() {
        return mhCureAccountService;
    }

    public void setMhCureAccountService(MHCureAccountService mhCureAccountService) {
        this.mhCureAccountService = mhCureAccountService;
    }

    public UserAccountValidator getUserAccountValidator() {
        return userAccountValidator;
    }

    public void setUserAccountValidator(UserAccountValidator userAccountValidator) {
        this.userAccountValidator = userAccountValidator;
    }

    private void init(UserAccount userAccount){
        userAccount.setAccountUserName(null);
        userAccount.setAccountPassword(null);
        userAccount.setToken(null);
        userAccount.setNewPassword(null);
        userAccount.setConfirmPassword(null);
    }
}