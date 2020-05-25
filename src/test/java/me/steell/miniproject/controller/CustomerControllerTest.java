package me.steell.miniproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.steell.miniproject.domain.Address;
import me.steell.miniproject.domain.Customer;
import me.steell.miniproject.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CustomerService customerService;

    @Test
    public void createEvent() throws Exception{
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
        String json = objectMapper.writeValueAsString(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

}