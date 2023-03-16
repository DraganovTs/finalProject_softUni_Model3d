package com.homecode.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriber_emails")
public class EmailSubscribersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;

    public EmailSubscribersEntity() {
    }

    public Long getId() {
        return id;
    }

    public EmailSubscribersEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmailSubscribersEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
