package com.homecode.library.service;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.IpAddress;
import com.homecode.library.repository.IpBlacklistRepository;
import com.homecode.library.service.impl.BlackListServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlackListServiceImplTest {

    private final String IP_ADDRESS = "123.456.789";
    @Mock
    private IpBlacklistRepository mockBlacklistRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Captor
    private ArgumentCaptor<IpAddress> ipAddressArgumentCaptor;
    private BlackListServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new BlackListServiceImpl(
                mockBlacklistRepository,
                mockModelMapper
        );
    }

    @Test
    void testIsBlackListed() {
        IpAddress ipAddress = new IpAddress()
                .setId(1L)
                .setIpAddress("123.456.789");

        when(mockBlacklistRepository.findByIpAddress(IP_ADDRESS))
                .thenReturn(Optional.of(ipAddress));

        Assertions.assertTrue(toTest.isBlacklisted(IP_ADDRESS));

    }

    @Test
    void testAddIpAddress(){

        toTest.addIpAddress(IP_ADDRESS);

        Mockito.verify(mockBlacklistRepository).save(ipAddressArgumentCaptor.capture());

        IpAddress ipAddressFromDb = ipAddressArgumentCaptor.getValue();

        Assertions.assertEquals(ipAddressFromDb.getIpAddress(),IP_ADDRESS);
    }




}
