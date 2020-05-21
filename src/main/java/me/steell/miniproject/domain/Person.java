package me.steell.miniproject.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("P")
@Getter @Setter
public class Person extends Customer {

    private String regnumber;
    private String mobilenumber;
    
}