package com.example.take_project.services;

import com.example.take_project.daos.UserDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserService implements UserServiceInterface{
    @EJB
    UserDao userDao;
}
