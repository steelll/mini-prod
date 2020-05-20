package me.steell.miniproject.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원이름은 필수")
    private String name;

    private String city;
    private String street;
    private String zipcode;
    
}