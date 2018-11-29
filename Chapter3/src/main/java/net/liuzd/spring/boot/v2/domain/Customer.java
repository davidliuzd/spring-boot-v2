package net.liuzd.spring.boot.v2.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private Long      id;

    private String    firstName;

    private LocalDate dateOfBirth;

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

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", dateOfBirth=" + dateOfBirth + "]";
    }

}