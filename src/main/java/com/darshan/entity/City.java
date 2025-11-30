package com.darshan.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "city_master")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer city_id;
    private String city_name;


    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

}
