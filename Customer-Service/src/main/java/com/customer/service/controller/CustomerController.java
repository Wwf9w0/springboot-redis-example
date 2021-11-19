package com.customer.service.controller;

import com.customer.service.model.Customer;
import com.customer.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public ResponseEntity<Object> getAllCustomers(){
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id){
       Optional <Customer> customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer){
        Customer addCustomer = customerService.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCustomer);
    }

    @PutMapping(value = "/customer")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer){
        Customer updateCustomer = customerService.update(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") Long id){
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }


}
