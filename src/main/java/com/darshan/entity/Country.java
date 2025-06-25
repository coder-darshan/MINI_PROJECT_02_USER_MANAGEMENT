package com.darshan.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "country_master")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;
}
