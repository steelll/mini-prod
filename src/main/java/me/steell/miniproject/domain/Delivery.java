package me.steell.miniproject.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name = "order_item")
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

}
