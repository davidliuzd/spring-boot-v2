package net.liuzd.spring.boot.v2.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "person")
public class Person {

    private @Id String id;
    private String     firstname;
    private String     lastname;
    private int        age;

    public Person(String firstname, String lastname, int age) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

}