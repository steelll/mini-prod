package me.steell.miniproject.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerForm {
    @NotEmpty(message = "필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    private int linecount;
    private String customertype;

    private String regnumber;
    private String mobilenumber;

    private String bizregnumber;
    private String representative;
    private String phoneno;
}