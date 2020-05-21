package me.steell.miniproject.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
    
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    
}