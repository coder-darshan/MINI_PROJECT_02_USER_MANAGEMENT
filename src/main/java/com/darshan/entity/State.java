package com.darshan.entity;

import com.darshan.entity.Country;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "state_master")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "state_name")
    private String stateName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
