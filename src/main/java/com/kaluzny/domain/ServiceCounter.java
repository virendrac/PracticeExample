package com.kaluzny.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String counterType;


    public ServiceCounter(Long id, String counterType) {
        this.id = id;
        this.counterType = counterType;
    }

    public ServiceCounter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounterType() {
        return counterType;
    }

    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    @Override
    public String toString() {
        return "ServiceCounter{" +
                "id=" + id +
                ", counterType='" + counterType + '\'' +
                '}';
    }
}
