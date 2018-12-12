package net.liuzd.spring.boot.v2.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Person {

    private @Id String   id;
    private final String firstname;
    private final String lastname;
    private final int    age;

    public Person(String firstname, String lastname, int age) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

}