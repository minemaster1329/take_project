package com.example.take_project.services;

import com.example.take_project.daos.ClientPackageDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ClientPackageInterface implements ClientPackageServiceInterface{
    @EJB
    ClientPackageDao clientPackageDao;
}
