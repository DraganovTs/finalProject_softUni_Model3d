package com.homecode.library.service.impl;

import com.homecode.library.model.IpAddress;
import com.homecode.library.model.dto.IpAddressDTO;
import com.homecode.library.repository.IpBlacklistRepository;
import com.homecode.library.service.BlackListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlackListServiceImpl implements BlackListService {
    private final IpBlacklistRepository blacklistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BlackListServiceImpl(IpBlacklistRepository blacklistRepository, ModelMapper modelMapper) {
        this.blacklistRepository = blacklistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isBlacklisted(String ipAddress) {
        return this.blacklistRepository.findByIpAddress(ipAddress).isPresent();
    }

    @Override
    public void addIpAddress(String ipAddress) {
        IpAddress ip = new IpAddress()
                .setIpAddress(ipAddress);
        this.blacklistRepository.save(ip);
    }

    @Override
    public void removeIpAddress(Long id) {
        this.blacklistRepository.deleteById(id);
    }

    @Override
    public List<IpAddressDTO> findAll() {
        return this.blacklistRepository.findAll()
                .stream()
                .map(i -> this.modelMapper
                        .map(i, IpAddressDTO.class))
                .collect(Collectors.toList());
    }
}
