package com.homecode.library.model.dto;

public class IpAddressDTO {

    public Long id;
    public String ipAddress;

    public IpAddressDTO() {
    }

    public Long getId() {
        return id;
    }

    public IpAddressDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public IpAddressDTO setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
}
