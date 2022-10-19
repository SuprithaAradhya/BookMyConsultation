package com.bookmyconsultation.bookmyconsultation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ACCESS_TOKEN", length = 1000)
    private String accessToken;

    private ZonedDateTime loginAt;

    private ZonedDateTime expiresAt;

    private ZonedDateTime logoutAt;
}
