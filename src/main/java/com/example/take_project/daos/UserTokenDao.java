package com.example.take_project.daos;

import com.example.take_project.models.UserToken;
import jakarta.ejb.Stateless;

@Stateless
public class UserTokenDao extends BasicCRUDDaoAbstract<UserToken>
        implements UserTokenDaoInterface{

    public UserTokenDao() {
        super(UserToken.class);
    }
}
