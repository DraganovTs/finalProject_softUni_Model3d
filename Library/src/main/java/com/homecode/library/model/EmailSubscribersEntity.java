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
}
