package com.darshan.service;

import com.darshan.dto.LoginFormDTO;
import com.darshan.dto.RegisterFormDTO;
import com.darshan.dto.ResetPwdFormDTO;
import com.darshan.dto.UserDTO;
import com.darshan.entity.City;
import com.darshan.entity.Country;
import com.darshan.entity.State;
import com.darshan.entity.User;
import com.darshan.repository.CityRepository;
import com.darshan.repository.CountryRepository;
import com.darshan.repository.StateRepository;
import com.darshan.repository.UserRepository;
import com.darshan.service.impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private CityRepository cityRepo;

    // Service method to get countries as a map of <country_id, country_name>
    @Override
    public Map<Integer, String> getCountries() {
        // Create a new empty map to store country ID and name
        Map<Integer, String> countryMap = new HashMap<>();

        // Retrieve all countries from the repository (database)
        List<Country> countryList = countryRepo.findAll();

        // Loop through each country and put its ID and name into the map
        countryList.stream().forEach(c ->
                countryMap.put(c.getCountryId(), c.getCountryName())
        );

        // Return the map containing all countries
        return countryMap;

        // This is alternative logic using Collectors.toMap (more concise way)
        // Map<Integer, String> countryMap = countryList.stream()
        //         .collect(Collectors.toMap(Country::getCountry_id, Country::getCountry_name));
    }

    // Service method to get states by country ID as a map of <state_id, state_name>
    @Override
    public Map<Integer, String> getStates(Integer countryId) {
        // Create a new empty map to store state ID and name
        Map<Integer, String> stateMap = new HashMap<>();

        // Retrieve states from the repository where country_id matches
        List<State> stateList = stateRepo.findByCountry_CountryId(countryId);

        // Loop through each state and put its ID and name into the map
        stateList.stream().forEach(s ->
                stateMap.put(s.getStateId(), s.getStateName())
        );

        // Return the map containing all states for the given country
        return stateMap;
    }

    // Service method to get cities by state ID as a map of <city_id, city_name>
    @Override
    public Map<Integer, String> getCites(Integer stateId) {
        // Create a new empty map to store city ID and name
        Map<Integer, String> cityMap = new HashMap<>();

        // Retrieve cities from the repository where state_id matches
        List<City> cityList = cityRepo.findByState_StateId(stateId);

        // Loop through each city and put its ID and name into the map
        cityList.stream().forEach(c ->
                cityMap.put(c.getCity_id(), c.getCity_name())
        );

        // Return the map containing all cities for the given state
        return cityMap;
    }

    // Service method to check if an email is already present in the database
    @Override
    public Boolean duplicateEmailCheck(String email) {
        // Query the repository to find a user by the given email
        User byEmail = userRepository.findByEmail(email);

        // If a user with the given email exists, return true (duplicate found)
        if (byEmail != null) {
            return true;
        } else {
            // If no user is found with the given email, return false (no duplicate)
            return false;
        }
    }


    @Override
    public Boolean saveUser(RegisterFormDTO registerDTO) {
        User userEntity = new User();
        //copying fata from one obj(register) to another(entity)

        //                       (source      ,destination)
        BeanUtils.copyProperties(registerDTO, userEntity);
        //above code work for normal class but in User we took country,state,city relational entity
        //so for that below code
        Country country = countryRepo.findById(registerDTO.getCountry().getCountryId()).orElse(null);
        userEntity.setCountry(country);

        State state = stateRepo.findById(registerDTO.getState().getStateId()).orElse(null);
        userEntity.setState(state);

        City city = cityRepo.findById(registerDTO.getCity().getCity_id()).orElse(null);
        userEntity.setCity(city);

        String randomPwd = generateRandomPwd();

        userEntity.setPwd(randomPwd);
        userEntity.setPwd_updated("No");

//        userRepository.save(userEntity);

        User savedUser = userRepository.save(userEntity);

        if (null != savedUser.getUser_id()) {
            String subject = "Your Account Created";
            String body = "Your Password TO Login  :"+randomPwd;
            String to = registerDTO.getEmail();
            emailService.sendEmail(subject, body, to);

            return true;
        }
        return false;
    }


    @Override
    public UserDTO login(LoginFormDTO loginFormDTO) {

        User userEntity = userRepository.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPassword());

        if(userEntity != null)
        {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity,userDTO);
            return userDTO ;
        }

        return null;
    }


    @Override
    public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO) {

        String email= resetPwdFormDTO.getEmail();

        User byEmail = userRepository.findByEmail(email);




        return false;
    }

    private String generateRandomPwd() {

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";

        String alphabets = upperCase + lowerCase;

        Random random = new Random();

        StringBuffer generatedPwd = new StringBuffer();


        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(alphabets.length());
            generatedPwd.append(alphabets.charAt(randomIndex));

        }
        return generatedPwd.toString();


    }
}
