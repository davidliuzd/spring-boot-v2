package net.liuzd.spring.boot.v2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.liuzd.spring.boot.v2.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureTestDatabase
public class CustomerRepositoryIntegrationTests {

	@Autowired
	private CustomerRepository repository;

	@Test
	public void findAllCustomers() {
		assertThat(this.repository.findAll()).hasSize(2);
	}

	@Test
	public void findByNameWithMatch() {
		assertThat(this.repository.findByName("joan")).hasSize(1);
	}

	@Test
	public void findByNameWithNoMatch() {
		assertThat(this.repository.findByName("hugh")).isEmpty();
	}

}