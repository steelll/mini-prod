package me.steell.miniproject.controller;

import me.steell.miniproject.domain.Customer;
import me.steell.miniproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class CustomerFormValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerForm.class.isAssignableFrom(clazz);
    }

    CustomerRepository customerRepository;

    public void setCustomerRepository( CustomerRepository customerRepository ){
        this.customerRepository = customerRepository;
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomerForm customerForm = (CustomerForm)target;
        if( customerForm == null || customerForm.getName() == null || "".equals( customerForm.getName().trim() ) ){
            errors.rejectValue("name", "require.name");
        }

        if( "PERSON".equals( customerForm.getCustomertype() ) ) {
            final List<Customer> findPersons = customerRepository.findByRegnumber(customerForm.getRegnumber());
            if (!findPersons.isEmpty()) {
                errors.rejectValue("regnumber", "이미 등록되어 있습니다.");
            }
        }else {
            final List<Customer> findCompanys = customerRepository.findByBizregnumber(customerForm.getBizregnumber());
            if (!findCompanys.isEmpty()) {
                errors.rejectValue("bizregnumber", "이미 등록되어 있습니다.");
            }
        }

    }
}
