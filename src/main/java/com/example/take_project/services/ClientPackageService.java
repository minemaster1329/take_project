package com.example.take_project.services;

import com.example.take_project.daos.ClientPackageDaoInterface;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ClientPackageInterface implements ClientPackageServiceInterface{
    @EJB
    ClientPackageDaoInterface clientPackageDao;
}
