package me.steell.miniproject.repository;

import lombok.Getter;
import lombok.Setter;
import me.steell.miniproject.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
    
}
