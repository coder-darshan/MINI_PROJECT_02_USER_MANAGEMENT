package com.darshan.controller;

import com.darshan.dto.*;
import com.darshan.service.DashBoardServiceImpl;
import com.darshan.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
//don
@Controller
public class UserController {

   private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DashBoardServiceImpl dashBoardService;

    @GetMapping("/register")
    public String loadRegisterPage(Model model) {
        logger.info("********register() started********");
        Map<Integer, String> countriesMap = userService.getCountries();
        model.addAttribute("country", countriesMap);

        RegisterFormDTO formDTO = new RegisterFormDTO();
        model.addAttribute("formDto", formDTO);

        logger.info("********register() completed********");
        return "register";
    }

    @GetMapping("/state/{countryId}")
    @ResponseBody
    public Map<Integer, String> getState(@PathVariable Integer countryId, Model model) {
        Map<Integer, String> states = userService.getStates(countryId);
        model.addAttribute("state", states);

        return states;
    }
//conflict
    @GetMapping("/city/{stateId}")
    @ResponseBody
    public Map<Integer, String> getCity(@PathVariable Integer stateId, Model model) {
        Map<Integer, String> cites = userService.getCites(stateId);
        model.addAttribute("city", cites);
        return cites;
    }

    @PostMapping("/register")
    public String handleRegistration(RegisterFormDTO registerFormDTO, Model model) {
        Boolean status = userService.duplicateEmailCheck(registerFormDTO.getEmail());

        if (status == true) {
            model.addAttribute("emsg", "Duplicate Email Found");
        } else {
            Boolean saveUser = userService.saveUser(registerFormDTO);
            if (saveUser) {
                //user Saved
                model.addAttribute("smsg", "Registration Success, Please check your Email...!");
            } else {
                //Failed
                model.addAttribute("emsg", "Registration Failed");
            }
        }
        // Reload countries for the form
        model.addAttribute("formDto",new RegisterFormDTO());
        model.addAttribute("country", userService.getCountries());
        return "register";
    }

    @GetMapping("/")
    public String index(Model model) {
        LoginFormDTO loginForm = new LoginFormDTO();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/login")
    public String HandleLogin(LoginFormDTO loginFormDTO, Model model) {
        UserDTO userDTO = userService.login(loginFormDTO);

        if (userDTO == null) {
            model.addAttribute("emsg", "Invalid Credentials");
            model.addAttribute("loginForm", new LoginFormDTO());
        } else {
            String pwdUpdated = userDTO.getPwd_updated();
            if ("Yes".equals(pwdUpdated)) {
                //display dashboard
                return "redirect:dashboard";
            } else {
//                display reset pwd page
                return "redirect:reset-pwd-page?email="+userDTO.getEmail();
            }
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        QuoteApiResponseDTO quote = dashBoardService.getQuote();
        model.addAttribute("quote", quote);

        return "dashboard";
    }

    @GetMapping("/reset-pwd-page")
    public String loadResetPassPage(@RequestParam("email") String email, Model model)
    {
        ResetPwdFormDTO resetPwdFormDTO = new ResetPwdFormDTO();
        resetPwdFormDTO.setEmail(email);
        model.addAttribute("resetPwd",resetPwdFormDTO);
        return "resetPwd";
    }

//    @PostMapping("resetPwd")
//    public String handlePwdReset(ResetPwdFormDTO resetPwdFormDTO, Model model)
//    {
//        boolean resetPwd = userService.resetPwd(resetPwdFormDTO);
//        if(resetPwd)
//        {
//            return "redirect:dashboard";
//        }
//        else {
//            return "resetPwd";
//        }
//    }
    //DARSHAN
@PostMapping("resetPwd")
public String handlePwdReset(ResetPwdFormDTO resetPwdFormDTO, Model model) {
    boolean resetPwd = userService.resetPwd(resetPwdFormDTO);
    if (resetPwd) {
        return "redirect:dashboard";
    } else {
        // Add the form object back to the model for Thymeleaf binding
        model.addAttribute("resetPwd", resetPwdFormDTO);
        // Optionally, add an error message
        model.addAttribute("emsg", "Password reset failed. Please try again.");
        return "resetPwd";
    }
}
}












