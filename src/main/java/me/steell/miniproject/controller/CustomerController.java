package me.steell.miniproject.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "redirect:/customers";

    }

    @GetMapping("/customers")
    public String list(Model model){
        List<Customer> customers = customerService.findCustomers();
        model.addAttribute("customers", customers);
        return "customers/customerList";
    }

    @ResponseBody
    @GetMapping("/customers/{id}/edit")
    public HashMap updatCustomerForm(@PathVariable("id") Long id, Model model){
        
        Customer customer = (Customer) customerService.findOne(id);
        
        CustomerForm form = new CustomerForm();

        form.setId(customer.getId());
        form.setName(customer.getName());
        
        form.setCity(customer.getAddress().getCity());
        form.setStreet(customer.getAddress().getStreet());
        form.setZipcode(customer.getAddress().getZipcode());
        form.setLinecount(customer.getLinecount());
        form.setCustomertype(customer.getCustomertype());

        form.setRegnumber(customer.getRegnumber());
        form.setMobilenumber(customer.getMobilenumber());

        form.setBizregnumber(customer.getBizregnumber());
        form.setRepresentative(customer.getRepresentative());
        form.setPhoneno(customer.getPhoneno());

        

        HashMap result = new HashMap<>();
        result.put("count", 1);
        result.put("status", "SUCCESS");
        result.put("templates/message", "");
        try {
            model.addAttribute("form", form);
        } catch (Exception e) {
            //TODO: handle exception
            result.put("count", 0);
            result.put("status", "FAIL");
            result.put("templates/message", e.getMessage());
        }
        return result;
        //result.put("count", 1);


        //return "customers/updateCustomerForm";
    }

    @PostMapping("/customers/{id}/edit")
    public String updateCustomer(@ModelAttribute("form") CustomerForm form){
        
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        
        Customer customer = new Customer();
        customer.setId(form.getId());
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
        
        return "redirect:/customers";

    }
}