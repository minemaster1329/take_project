package com.example.take_project.services;

import com.example.take_project.daos.UserDao;
import com.example.take_project.daos.UserDaoInterface;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserService implements UserServiceInterface{
    @EJB
    UserDaoInterface userDao;
}
