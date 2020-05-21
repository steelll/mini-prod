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
        validateDuplicateCustomer(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void validateDuplicateCustomer(final Customer customer) {
        // exception
        final List<Customer> findPerson = customerRepository.findByRegnumber(customer.getRegnumber());
        if (!findPerson.isEmpty()) {
            throw new IllegalStateException("이미 등록되어 있습니다.");
            
        }

        final List<Customer> findCompany = customerRepository.findByBizregnumber(customer.getBizregnumber());
        if (!findCompany.isEmpty()) {
            throw new IllegalStateException("이미 등록되어 있습니다.");
            
        }
    }

}