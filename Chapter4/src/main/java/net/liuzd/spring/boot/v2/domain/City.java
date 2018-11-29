package net.liuzd.spring.boot.v2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;

    @Column(nullable = false)
    private String            name;

    @Column(nullable = false)
    private String            state;

    @Column(nullable = false)
    private String            country;

    @Column(nullable = false)
    private String            map;

    protected City() {}

    public City(String name, String state, String country, String map) {
        super();
        this.name = name;
        this.state = state;
        this.country = country;
        this.map = map;
    }

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public String getCountry() {
        return this.country;
    }

    public String getMap() {
        return this.map;
    }

    @Override
    public String toString() {
        return getName() + "," + getState() + "," + getCountry();
    }

}