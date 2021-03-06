package me.steell.miniproject.service;

import me.steell.miniproject.domain.Address;
import me.steell.miniproject.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void findCustomers() throws Exception{

        //샘플데이터 등록
        Address address = new Address("서울특별시", "종로구", "12345");

        Customer customer = new Customer();

        customer.setName("홍길동");
        customer.setAddress(address);
        customer.setLinecount(1);
        customer.setCustomertype("PERSON");

        customer.setRegnumber("801212-1");
        customer.setMobilenumber("010-1111-2222");

        customer.setBizregnumber("");
        customer.setRepresentative("");
        customer.setPhoneno("");
        customerService.join( customer );

        //고객조회 메서드 실행
        List<Customer> list = customerService.findCustomers("홍길동","PERSON");
        Assert.assertTrue( list.size() > 0  );

    }

}