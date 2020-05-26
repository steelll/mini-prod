package me.steell.miniproject.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.Customer;
import me.steell.miniproject.service.CustomerService;

@RestController
@RequiredArgsConstructor
public class CustomerApiController {

    private final CustomerService customerService;

    @GetMapping("/api/v1/customers")
    public List<Customer> customersV1(@RequestBody @Valid Customer customer) {
        String name = customer.getName();
        String customertype = customer.getCustomertype();

        return customerService.findCustomers(name, customertype);
    }

    @PostMapping("/api/v1/customers")
    public CreateCustomerResponse saveMemberV1(@RequestBody @Valid Customer customer) {
        Long id = customerService.join(customer);
        return new CreateCustomerResponse(id);
    }

    @Data
    static class CreateCustomerRequest {
        private String name;
    }

    @Data
    class CreateCustomerResponse {
        private Long id;

        public CreateCustomerResponse(Long id) {
            this.id = id;
        }
    }

    @GetMapping("/api/v2/customers")
    public Result customersV2() {
        List<Customer> findCustomers = customerService.findCustomers(null, null);
        // 엔티티 -> DTO 변환
        List<CustomerDto> collect = findCustomers.stream().map(m -> new CustomerDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class CustomerDto {
        private String name;
    }

    @PostMapping("/api/v2/customers")
    public CreateCustomerResponse saveCustomerV2(@RequestBody @Valid CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        Long id = customerService.join(customer);
        return new CreateCustomerResponse(id);
    }

    /**
     * 수정 API
     */
    @PutMapping("/api/v2/customers/{id}")
    public UpdateCustomerResponse updateCustomerV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateCustomerRequest request) {
        customerService.update(id, request.getName());
        Customer findCustomer = customerService.findOne(id);
        return new UpdateCustomerResponse(findCustomer.getId(), findCustomer.getName());
    }

    @Data
    static class UpdateCustomerRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    class UpdateCustomerResponse {
        private Long id;
        private String name;
    }

    // DELETE
    // 해당 ID의 사용자를 삭제
    @DeleteMapping
    public void delete(@RequestParam Long id) {
        customerService.deleteCustomer(id);
    }

}