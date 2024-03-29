package com.homecode.library.repository;

import com.homecode.library.model.EmailSubscribersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSubscriberRepository extends JpaRepository<EmailSubscribersEntity, Long> {
}
