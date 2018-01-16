package com.kaluzny.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long customerId;
    private String typeOfService;
    private int priority;
    private long serviceCounterId;

    public Token(Long id, Long customerId, String typeOfService, int priority) {
        this.id = id;
        this.customerId = customerId;
        this.typeOfService = typeOfService;
        this.priority = priority;
    }

    public Token() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getServiceCounterId() {
        return serviceCounterId;
    }

    public void setServiceCounterId(long serviceCounterId) {
        this.serviceCounterId = serviceCounterId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", typeOfService='" + typeOfService + '\'' +
                ", priority=" + priority +
                ", serviceCounterId=" + serviceCounterId +
                '}';
    }
}
