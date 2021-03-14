package com.SmartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SmartContactManager.entities.User;

public interface UserRepositiory extends JpaRepository< User,Integer> {

}
