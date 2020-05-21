package me.steell.miniproject.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.Address;
import me.steell.miniproject.domain.Customer;
import me.steell.miniproject.service.CustomerService;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService customerService;

    @GetMapping("/customers/new")
    public String createForm(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customers/createCustomerForm";
    }

    @PostMapping("/customers/new")
    public String create(@Valid CustomerForm form, BindingResult result){

        if (result.hasErrors()){
            return "customers/createCustomerForm";
        }
        
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        
        Customer customer = new Customer();
        customer.setName(form.getName());
        customer.setAddress(address);
        customer.setLinecount(form.getLinecount());
        customer.setCustomertype(form.getCustomertype());

        customer.setRegnumber(form.getRegnumber());
        customer.setMobilenumber(form.getMobilenumber());

        customer.setBizregnumber(form.getBizregnumber());
        customer.setRepresentative(form.getRepresentative());
        customer.setPhoneno(form.getPhoneno());
        
        customerService.join(customer);
        return "redirect:/";

    }
}