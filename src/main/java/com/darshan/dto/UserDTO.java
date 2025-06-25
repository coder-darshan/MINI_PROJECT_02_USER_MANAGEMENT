package com.darshan.dto;

import com.darshan.entity.City;
import com.darshan.entity.Country;
import com.darshan.entity.State;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
public class UserDTO {


    private Integer user_id;

    private String email;

    private String pwd;

    private String pwd_updated;

}
