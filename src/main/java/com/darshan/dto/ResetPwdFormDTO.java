package com.darshan.dto;

import com.darshan.entity.City;
import com.darshan.entity.Country;
import com.darshan.entity.State;
import lombok.Data;

@Data
public class ResetPwdFormDTO {

    private String email;

    private String old_pwd;

    private String new_pwd;

    private String conf_pwd;

}
