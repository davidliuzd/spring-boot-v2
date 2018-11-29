package net.liuzd.spring.boot.v2.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private Long      id;

    private String    firstName;

    private LocalDateTime dateOfBirth;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", dateOfBirth=" + dateOfBirth + "]";
    }

}