package com.darshan.repository;

import com.darshan.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

//    public List<City> findByStateId(Integer stateId);

    List<City> findByState_StateId(Integer stateId);
}
