package me.steell.miniproject.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Customer {
    
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private int linecount;
    private String customertype;

    private String regnumber;
    private String mobilenumber;

    private String bizregnumber;
    private String representative;
    private String phoneno;
   
}