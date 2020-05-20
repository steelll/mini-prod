package me.steell.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.Delivery;
import me.steell.miniproject.domain.Member;
import me.steell.miniproject.domain.Order;
import me.steell.miniproject.domain.OrderItem;
import me.steell.miniproject.domain.item.Item;
import me.steell.miniproject.repository.ItemRepository;
import me.steell.miniproject.repository.MemberRepository;
import me.steell.miniproject.repository.OrderRepository;
import me.steell.miniproject.repository.OrderSearch;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //order
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //Entity 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.fidnOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.Createorder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //cancel
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    //search
    
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
    
    
}