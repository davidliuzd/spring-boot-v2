package net.liuzd.spring.boot.v2.repository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import net.liuzd.spring.boot.v2.domain.Person;


public interface RxJava2PersonRepository extends RxJava2CrudRepository<Person, String> {

	/**
	 * Derived query selecting by {@code lastname}.
	 *
	 * @param lastname
	 * @return
	 */
	Flowable<Person> findByLastname(String lastname);

	/**
	 * String query selecting one entity.
	 *
	 * @param lastname
	 * @return
	 */
	@Query("{ 'firstname': ?0, 'lastname': ?1}")
	Maybe<Person> findByFirstnameAndLastname(String firstname, String lastname);

	/**
	 * Derived query selecting by {@code lastname}. {@code lastname} uses deferred resolution that does not require
	 * blocking to obtain the parameter value.
	 *
	 * @param lastname
	 * @return
	 */
	Flowable<Person> findByLastname(Single<String> lastname);

	/**
	 * Derived query selecting by {@code firstname} and {@code lastname}. {@code firstname} uses deferred resolution which
	 * does not require blocking to obtain the parameter value.
	 *
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	Maybe<Person> findByFirstnameAndLastname(Single<String> firstname, String lastname);

	/**
	 * Use a tailable cursor to emit a stream of entities as new entities are written to the capped collection.
	 *
	 * @return
	 */
	@Tailable
	Flowable<Person> findWithTailableCursorBy();
}