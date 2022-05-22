package com.example.take_project.services;

import com.example.take_project.daos.ClientDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ClientService implements ClientServiceInterface{
    @EJB
    ClientDao clientDao;
}
