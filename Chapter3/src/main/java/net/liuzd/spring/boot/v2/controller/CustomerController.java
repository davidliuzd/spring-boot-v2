package net.liuzd.spring.boot.v2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.liuzd.spring.boot.v2.domain.Customer;
import net.liuzd.spring.boot.v2.repository.CustomerRepository;

@Controller
public class CustomerController {

	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Customer> customers(@RequestParam String name) {
		return this.customerRepository.findByName(name);
	}

}