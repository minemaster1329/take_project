package com.example.take_project.services;

import com.example.take_project.daos.ClientDao;
import com.example.take_project.daos.ClientDaoInterface;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ClientService implements ClientServiceInterface{
    @EJB
    ClientDaoInterface clientDao;
}
