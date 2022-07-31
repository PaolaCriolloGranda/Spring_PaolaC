package com.example.spring.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.app.entity.User;

@Repository
//heredo e indico mi objeto
public interface UserDao extends JpaRepository<User, Long>{

}
