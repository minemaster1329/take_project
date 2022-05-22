package com.example.take_project.services;

import com.example.take_project.daos.DefinedRouteDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class DefinedRouteService implements DefinedRouteServiceInterface{
    @EJB
    DefinedRouteDao definedRouteDao;
}
