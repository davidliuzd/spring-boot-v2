package net.liuzd.spring.boot.v2.repository;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.liuzd.spring.boot.v2.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("select id, first_name, date_of_birth from customer where first_name LIKE CONCAT ('%',:name,'%')")
	List<Customer> findByName(@Param("name") String name);

}