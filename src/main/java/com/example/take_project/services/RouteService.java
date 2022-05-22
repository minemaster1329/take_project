package com.example.take_project.services;

import com.example.take_project.daos.RouteDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class RouteService implements RouteServiceInterface{
    @EJB
    RouteDao routeDao;
}
