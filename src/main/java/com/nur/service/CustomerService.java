package com.nur.service;

import com.nur.dto.CustomerRequest;
import com.nur.dto.CustomerResponse;
import com.nur.entity.Customer;
import com.nur.exceptions.CustomerNotFoundException;
import com.nur.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNo(customerRequest.getPhoneNo());
        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        return mapToResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers(String firstName) {
        if (firstName == null)
            return customerRepository.findAll().stream().map(this::mapToResponse).toList();
        return customerRepository.findByFirstName(firstName).stream().map(this::mapToResponse).toList();
    }

    @Transactional
    public String deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
        return "Customer deleted with customerId: " + customerId;
    }


    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhoneNo(customer.getPhoneNo());
        return response;
    }
}
