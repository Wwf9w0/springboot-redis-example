package com.customer.service.service;

import com.customer.service.model.Customer;
import com.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "customerCache")
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    @Cacheable(cacheNames = "customers")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @CacheEvict(cacheNames = "customers" , allEntries = true)
    public Customer add (Customer customer){
        return customerRepository.save(customer);
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    public Customer update(Customer customer){

        Optional<Customer> optCustomer = customerRepository.findById(customer.getId());
        Customer repCustomer = optCustomer.get();
        if (optCustomer.isPresent()){
            repCustomer.setName((customer.getName()));
            repCustomer.setAddress(customer.getAddress());
            repCustomer.setCity(customer.getCity());
            repCustomer.setCountry(customer.getCountry());
            repCustomer.setContactName(customer.getContactName());
            repCustomer.setPostalCode(customer.getPostalCode());
            customerRepository.save(repCustomer);
        }
;

        return customerRepository.save(customer);
    }

    @Caching(evict = { @CacheEvict(cacheNames = "customer", key = "#id"),
    @CacheEvict(cacheNames = "custmers" , allEntries = true)})
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "customer", key = "#id", unless = "#result == null ")
    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }


}
