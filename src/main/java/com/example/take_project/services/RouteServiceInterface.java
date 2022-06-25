package com.example.take_project.services;

import com.example.take_project.dto.clientpackage.NewRouteCarsDefinedRouteDto;
import com.example.take_project.models.DefinedRoute;
import com.example.take_project.models.Route;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;

public interface RouteServiceInterface extends BasicCRUDServiceInterface<Route> {
    void addNew(NewRouteCarsDefinedRouteDto newRouteCarsDefinedRouteDto) throws EntityNotFoundException;
}
