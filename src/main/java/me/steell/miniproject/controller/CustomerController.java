package me.steell.miniproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String create(@Valid CustomerForm form, BindingResult result, final HttpServletRequest req){

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


        CustomerFormValidation customerFormValidation = new CustomerFormValidation();
        customerFormValidation.setCustomerRepository( customerService.getCustomerRepository() );
        customerFormValidation.validate( form, result );

        if( result.hasErrors() ){
           //return "redirect:/customers/new";
            return "redirect:/customers/new";
        }

        customerService.join(customer);
        return "redirect:/customers";

    }

    @GetMapping("/customers")
    public String list(Model model, HttpServletRequest request){


        String customername = "";
        String customertype = "";
        if( request.getParameter("customername") != null ) {
            customername = request.getParameter("customername");
        }
        if( request.getParameter("customertype") != null ) {
            customertype = request.getParameter("customertype");
        }
        model.addAttribute("customername", customername);

        List<Customer> customers = customerService.findCustomers( customername, customertype  );
        model.addAttribute("customers", customers);
        return "customers/customerList";
    }

    @GetMapping("/customers/{id}")
    public String CustomerForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        return "customers/updateCustomerForm";
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

        ArrayList list = new ArrayList<HashMap>();
        HashMap data = new HashMap();
        HashMap address = new HashMap();
        address.put("city", customer.getAddress().getCity());
        address.put("street", customer.getAddress().getStreet());
        address.put("zipcode", customer.getAddress().getZipcode());


        data.put("id", customer.getId());
        data.put("name", customer.getName());
        data.put("address", address);
        data.put("linecount", customer.getLinecount());

        data.put("regnumber", customer.getRegnumber());
        data.put("mobilenumber", customer.getMobilenumber());

        data.put("bizregnumber", customer.getBizregnumber());
        data.put("representative", customer.getRepresentative());
        data.put("phoneno", customer.getPhoneno());
        
        list.add( data );

        HashMap result = new HashMap<>();
        result.put("count", customer != null ? 1 : 0 );
        result.put("data", list);
        return result;
    }

    @ResponseBody
    @PostMapping("/customers/{id}/edit")
    public HashMap updateCustomer(@RequestBody CustomerForm form){

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
        
        HashMap result = new HashMap<>();
        result.put("count", 1);
        result.put("status", "SUCCESS");
        result.put("message", "");
        try {
            customerService.join(customer);
        } catch (Exception e) {
            result.put("count", 0);
            result.put("status", "FAIL");
            result.put("message", e.getMessage());
        }
        return result;

    }

    
}