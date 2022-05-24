package com.example.take_project.services;

import com.example.take_project.daos.UserTokenDaoInterface;
import com.example.take_project.models.UserToken;
import com.example.take_project.otherstuff.exceptions.UserTokenNotFoundException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.Optional;

@Stateless
public class UserTokenService implements UserTokenServiceInterface{
    @EJB
    UserTokenDaoInterface userTokenDao;

    @Override
    public UserToken getTokenByName(String name) throws UserTokenNotFoundException {
        Optional<UserToken> userToken = userTokenDao.getAll().stream().filter(token -> token.getToken().equals(name)).findFirst();

        if (userToken.isPresent()) return userToken.get();
        else throw new UserTokenNotFoundException();
    }
}
