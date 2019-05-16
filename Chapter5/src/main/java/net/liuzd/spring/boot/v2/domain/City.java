package net.liuzd.spring.boot.v2.domain;

import java.io.Serializable;

public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            name;

    private String            state;

    private String            country;

    private String            map;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", state=" + state + ", country=" + country + ", map=" + map + "]";
    }

}