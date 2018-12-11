package net.liuzd.spring.boot.v2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.liuzd.spring.boot.v2.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findByFirstName(String firstName);

	List<Customer> findByLastName(String lastName);

}
