package com.homecode.library.service;

import com.homecode.library.model.dto.IpAddressDTO;

import java.util.List;

public interface BlackListService {


    public boolean isBlacklisted(String ipAddress);

    void addIpAddress(String ipAddress);
    void removeIpAddress(Long id);

    List<IpAddressDTO>findAll();
}
