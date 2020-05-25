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
        // validateDuplicateCustomer(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    // 회원전체 조회
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Customer findOne(final Long id) {
        return customerRepository.findOne(id);
    }

	public List<Customer> findByName (final String name) {
        return customerRepository.findByName(name);
    }

   private void validateDuplicateCustomer(final Customer customer) {

       final List<Customer> findPersons = customerRepository.findByRegnumber(customer.getRegnumber());
       if (!findPersons.isEmpty()) {
           throw new IllegalStateException("이미 등록되어 있습니다.");

       }

       final List<Customer> findCompanys = customerRepository.findByBizregnumber(customer.getBizregnumber());
       if (!findCompanys.isEmpty()) {
           throw new IllegalStateException("이미 등록되어 있습니다.");

       }

   }

}