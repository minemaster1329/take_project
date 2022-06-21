package com.example.take_project.daos;

import com.example.take_project.models.User;
import jakarta.ejb.Stateless;

@Stateless
public class UserDao extends BasicCRUDDaoAbstract<User>
        implements UserDaoInterface{

    public UserDao() {
        super(User.class);
    }

}
