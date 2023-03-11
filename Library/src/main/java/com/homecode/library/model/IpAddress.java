package com.homecode.library.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ipAddresses")
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String ipAddress;


    public IpAddress() {
    }

    public Long getId() {
        return id;
    }

    public IpAddress setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public IpAddress setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
}
