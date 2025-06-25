package com.darshan.service.impl;

import com.darshan.dto.LoginFormDTO;
import com.darshan.dto.RegisterFormDTO;
import com.darshan.dto.ResetPwdFormDTO;
import com.darshan.dto.UserDTO;

import java.util.Map;

public interface UserService {

    public Map<Integer,String>getCountries();

    public Map<Integer,String>getStates(Integer countryId);

    public Map<Integer,String>getCites(Integer stateId);

    public Boolean duplicateEmailCheck(String email);

    public Boolean saveUser(RegisterFormDTO registerFormDTO);

    public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO);

    public UserDTO login(LoginFormDTO loginFormDTO);

}
