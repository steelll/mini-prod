package me.steell.miniproject.service;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import me.steell.miniproject.domain.Address;
import me.steell.miniproject.domain.Member;
import me.steell.miniproject.domain.Order;
import me.steell.miniproject.domain.OrderStatus;
import me.steell.miniproject.domain.item.Book;
import me.steell.miniproject.domain.item.Item;
import me.steell.miniproject.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired    EntityManager em;
    @Autowired    OrderService orderService;
    @Autowired    OrderRepository orderRepository;

    @Test
    public void itemOrder() throws Exception{
        //given
        Member member = createMember();

        Book book = createBook("testjpa", 100000, 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품주문시 상태는 order", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품종류 수가 정확", 1, getOrder.getOrderItems().size());
        assertEquals("주문한 가격은 가격 * 수량 ", 100000*orderCount, getOrder.getTotPrice());
        assertEquals("주문 수량 만큼 재고가 줄어든다",8,book.getStockQuantity());

    }

    @Test
    public void exceedOrder() throws Exception{
        Member member = createMember();
        Item item = createBook("testjpa", 100000, 10);

        int orderCount = 11;

        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("재고 수량 부족 에러가 발생해야 함");
    }

    @Test
    public void cancelOrder() throws Exception{
        //given
        Member member = createMember();
        Book item = createBook("jpa", 100000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문취소시 상태", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문취소시 재고가 원복", 10, item.getStockQuantity());
        
    }

    private Book createBook(String name, int price, int stock) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stock);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","경기","123-123"));
        em.persist(member);
        return member;
    }

}