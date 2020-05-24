package me.steell.miniproject.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomerFormValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomerForm customerForm = (CustomerForm)target;
        if( customerForm == null || customerForm.getName() == null || "".equals( customerForm.getName().trim() ) ){
            errors.rejectValue("name", "require.name");
        }

    }
}
