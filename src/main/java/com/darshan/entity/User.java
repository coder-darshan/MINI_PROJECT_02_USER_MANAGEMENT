package com.darshan.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "user_master")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String u_name;
    private String email;
    private Long ph_no;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id ")
    private City city;

    private String pwd;
    private String pwd_updated;

    @CreationTimestamp
    @Column(name = "created_date",updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date",insertable = false)
    private LocalDate updatedDate;
}
