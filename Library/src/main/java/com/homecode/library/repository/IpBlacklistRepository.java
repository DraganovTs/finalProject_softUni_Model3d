package com.homecode.library.repository;

import com.homecode.library.model.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpBlacklistRepository extends JpaRepository<IpAddress,Long> {

    Optional<IpAddress>findByIpAddress(String ipAddress);
}
