package com.darshan.repository;

import com.darshan.entity.Country;
import com.darshan.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State,Integer > {

//    public List<State> findByCountryId(Integer countryId);

//    List<State> findByCountry_CountryId(Integer countryId);

    List<State> findByCountry_CountryId(Integer countryId);

}
