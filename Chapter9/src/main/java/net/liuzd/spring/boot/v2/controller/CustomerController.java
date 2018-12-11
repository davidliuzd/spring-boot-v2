package net.liuzd.spring.boot.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.liuzd.spring.boot.v2.domain.Customer;
import net.liuzd.spring.boot.v2.repository.CustomerRepository;

@Controller
@RequestMapping("/api")
/**
 * 如对象前面加上：@RequestBody，会报以下错误
 * {
    "timestamp": "2018-12-11T09:29:36.288+0000",
    "status": 415,
    "error": "Unsupported Media Type",
    "message": "Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported",
    "path": "/api/customers/create"
}
 * */
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "customers", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Customer> getAllCustomers() {
        System.out.println("Get all Customers...");
        return customerRepository.findAll();
    }

    @PostMapping(value = "/customers/create", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Customer> createCustomer(@Valid /*@RequestBody*/ Customer customer) {
        System.out.println("Create Customer: " + customer.getName() + "...");
        customer.setActive(false);
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    @PostMapping(value = "/customers/update/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, Customer customer) {
        System.out.println("Update Customer with ID = " + id + "...");
        Customer customerData = customerRepository.findById(id).get();
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerData.setName(customer.getName());
        customerData.setAge(customer.getAge());
        customerData.setActive(customer.isActive());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());
        Customer updatedcustomer = customerRepository.save(customerData);
        return new ResponseEntity<>(updatedcustomer, HttpStatus.OK);
    }

    @PostMapping("/customers/del/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
        System.out.println("Delete Customer with ID = " + id + "...");
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    @PostMapping("/customers/deleteAll")
    @ResponseBody
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All Customers...");
        customerRepository.deleteAll();
        return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
    }
}