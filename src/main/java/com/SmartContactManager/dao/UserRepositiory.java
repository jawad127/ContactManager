package com.SmartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SmartContactManager.entities.User;

@Repository
public interface UserRepositiory extends JpaRepository< User,Integer> {

}
