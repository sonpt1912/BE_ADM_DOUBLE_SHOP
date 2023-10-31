package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null); // Returns null if customer is not found
    }
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setUsername(customer.getUsername());
            existingCustomer.setName(customer.getName());
            existingCustomer.setGender(customer.getGender());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setBirtDay(customer.getBirtDay());
            existingCustomer.setStatus(customer.getStatus());
            existingCustomer.setCreatedBy(customer.getCreatedBy());
            existingCustomer.setUpdated_by(customer.getUpdated_by());
            existingCustomer.setCreatedTime(customer.getCreatedTime());
            // Set other properties of Customer as needed

            return customerRepository.save(existingCustomer);
        }
        // Handle not found case if needed, such as throwing an exception
        return null;
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> searchCustomer(String keyword) {
        return customerRepository.findByUsernameContainingIgnoreCase(keyword);
    }

    public List<Customer> sapXep() {
        return customerRepository.findAllByOrderByUsername();
    }
}