package net.liuzd.spring.boot.v2.domain;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    public interface WithoutPasswordView {};

    public interface WithPasswordView extends WithoutPasswordView {};

    private Long   id;
    private String name;
    private String pwd;
    private int    age;

    public User() {}

    @JsonView(WithoutPasswordView.class)
    public String getName() {
        return this.name;
    }

    @JsonView(WithPasswordView.class)
    public String getPwd() {
        return this.pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User(String name, String pwd) {
        super();
        this.name = name;
        this.pwd = pwd;
    }

    public User(Long id, String name, String pwd, int age) {
        super();
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

   

}