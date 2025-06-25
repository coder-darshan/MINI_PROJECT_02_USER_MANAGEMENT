package com.darshan.dto;

import com.darshan.entity.City;
import com.darshan.entity.Country;
import com.darshan.entity.State;
import lombok.Data;

@Data
public class RegisterFormDTO {

    private Integer user_id;

    private String u_name;

    private String email;

    private Long ph_no;

    private Country country;

    private State state;

    private City city;

    private String pwd;

    private String pwd_updated;
}
