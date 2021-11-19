package com.customer.service.Customer.Service.repository;

import com.customer.service.Customer.Service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
