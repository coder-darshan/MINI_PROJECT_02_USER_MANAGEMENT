package com.darshan.controller;

import com.darshan.dto.LoginFormDTO;
import com.darshan.dto.RegisterFormDTO;
import com.darshan.dto.ResetPwdFormDTO;
import com.darshan.dto.UserDTO;
import com.darshan.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/country")
    public ResponseEntity<Map<Integer, String>> countryList() {
        Map<Integer, String> countries = userService.getCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/state")
    public ResponseEntity<Map<Integer, String>> stateList(@RequestParam Integer countryId) {
        return new ResponseEntity<>(userService.getStates(countryId), HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<Map<Integer, String>> getCites(@RequestParam Integer stateId) {

        return new ResponseEntity<>(userService.getCites(stateId), HttpStatus.OK);
    }

    @GetMapping("/emailCheck")
    public ResponseEntity<Boolean> emailCheck(@RequestBody String email) {

        return new ResponseEntity<>(userService.duplicateEmailCheck(email), HttpStatus.OK);
    }

    @PostMapping("addUser")
    public ResponseEntity<Boolean> saveData(@RequestBody RegisterFormDTO registerDTO) {

        return new ResponseEntity<>(userService.saveUser(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginMethod(@RequestBody LoginFormDTO loginFormDTO) {

        return new ResponseEntity<>(userService.login(loginFormDTO), HttpStatus.CREATED);

    }

    @PostMapping("/resetPass")
    public ResponseEntity<Boolean> reset(@RequestBody ResetPwdFormDTO resetPwdFormDTO) {

        return new ResponseEntity<>(userService.resetPwd(resetPwdFormDTO),HttpStatus.CREATED);
    }
}