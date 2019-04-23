package net.liuzd.spring.boot.v2.dao;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import net.liuzd.spring.boot.v2.domain.Customer;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

	Customer findByFirstName(String firstName);

	List<Customer> findByLastName(String lastName);

}