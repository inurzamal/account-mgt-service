package com.nur.controller;

import com.nur.dto.CustomerRequest;
import com.nur.dto.CustomerResponse;
import com.nur.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse createdCustomer = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerById(id);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } catch (ResponseStatusException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(@RequestParam(required = false) String firstName) {
        List<CustomerResponse> allCustomers = customerService.getAllCustomers(firstName);
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @PostMapping("/customer/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId){
        try {
            String response = customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
