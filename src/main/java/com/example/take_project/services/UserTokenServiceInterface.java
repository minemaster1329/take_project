package com.example.take_project.services;

import com.example.take_project.models.UserToken;
import com.example.take_project.otherstuff.exceptions.UserTokenNotFoundException;

public interface UserTokenServiceInterface {
    UserToken getTokenByName(String name) throws UserTokenNotFoundException;
}
