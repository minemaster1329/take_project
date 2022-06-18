package com.example.take_project.services;

import com.example.take_project.models.DefinedRoute;
import com.example.take_project.models.Route;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface RouteServiceInterface extends BasicCRUDServiceInterface<Route> {
    public List<Route> getRoutesForCarInSpecifiedDay(Long carId, Date date) throws EntityNotFoundException;
}
