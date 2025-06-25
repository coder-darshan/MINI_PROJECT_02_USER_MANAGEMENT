package com.darshan.repository;

import com.darshan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer > {

    public User findByEmail(String email);

    public User findByEmailAndPwd(String email,String password);
}
