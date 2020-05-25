package me.steell.miniproject.controller;

// import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerForm {

    private Long id;
    private String name;

    private String customertype;
    
    private String city;
    private String street;
    private String zipcode;

    private int linecount;
    
    private String regnumber;
    private String mobilenumber;

    private String bizregnumber;
    private String representative;
    private String phoneno;
}