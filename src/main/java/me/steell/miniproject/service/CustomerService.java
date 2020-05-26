package me.steell.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.Customer;
import me.steell.miniproject.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    //회원가입
    @Transactional
    public Long join(final Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }

    // 회원전체 조회
    public List<Customer> findCustomers(String customername, String customertype ) {
        return customerRepository.findAll( customername,  customertype );
    }

    public Customer findOne(final Long id) {
        return customerRepository.findOne(id);
    }

	public List<Customer> findByName (final String name) {
        return customerRepository.findByName(name);
    }

    public CustomerRepository getCustomerRepository(){
        return customerRepository;
    }

    
}