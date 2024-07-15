package com.nur.repository;

import com.nur.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @Query("SELECT * FROM customer WHERE first_name = :firstName") // here, exact db column name is first_name
//    @Query("SELECT c FROM Customer c WHERE c.firstName = :firstName")

//    @Query(value = "SELECT * FROM customer WHERE first_name LIKE %:firstName%", nativeQuery = true)
    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %:firstName%")
    List<Customer> findByFirstName(String firstName);
}
