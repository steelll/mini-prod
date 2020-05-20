package me.steell.miniproject.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customertype")
public class Customer {
    
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;
}