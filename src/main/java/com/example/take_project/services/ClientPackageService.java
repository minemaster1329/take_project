package com.example.take_project.services;

import com.example.take_project.daos.ClientPackageDaoInterface;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ClientPackageService implements ClientPackageServiceInterface{
    @EJB
    ClientPackageDaoInterface clientPackageDao;
}
