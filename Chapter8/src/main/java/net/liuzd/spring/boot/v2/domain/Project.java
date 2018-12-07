package net.liuzd.spring.boot.v2.domain;

import java.io.Serializable;

public class Project implements Serializable {

    private static final long serialVersionUID = -1L;

    private String            name;

    private int               counts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public Project(String name, int counts) {
        super();
        this.name = name;
        this.counts = counts;
    }

    public Project() {
        super();
    }

}
