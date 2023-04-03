package com.medhead.urgencyManagement.entity;

public class Pathology {
    private Integer id;

    private String name;

    public Pathology(Integer id,
                     String name
    ) {
        this.id = id;
        this.name = name;
    }

    public Pathology() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String pathologies) {
        this.name = pathologies;
    }

    @Override
    public String toString() {
        return "Pathology{" +
                "id=" + id +
                ", pathologies='" + name + '\'' +
                '}';
    }
}
