package com.example.take_project.services;

import com.example.take_project.daos.UserTokenDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserTokenService implements UserTokenServiceInterface{
    @EJB
    UserTokenDao userTokenDao;
}
