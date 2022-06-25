package com.example.take_project.services;

import com.example.take_project.daos.CarDaoInterface;
import com.example.take_project.daos.DefinedRouteDaoInterface;
import com.example.take_project.daos.RouteDaoInterface;
import com.example.take_project.dto.clientpackage.NewRouteCarsDefinedRouteDto;
import com.example.take_project.models.Car;
import com.example.take_project.models.DefinedRoute;
import com.example.take_project.models.Route;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.sql.Date;
import java.util.List;

@Stateless
public class RouteService implements RouteServiceInterface{
    @EJB
    RouteDaoInterface routeDao;
    @EJB
    CarDaoInterface carDaoInterface;
    @EJB
    DefinedRouteDaoInterface definedRouteDaoInterface;

    @Override
    public Route getById(Long id) {
        return routeDao.getById(id);
    }

    @Override
    public List<Route> getAll() {
        return routeDao.getAll();
    }

    @Override
    public void addNew(Route cp) {
        routeDao.add(cp);
    }

    @Override
    public void addNew(NewRouteCarsDefinedRouteDto newRouteCarsDefinedRouteDto) throws EntityNotFoundException {
        Route newRoute = new Route();

        Car chosenCar = carDaoInterface.getById(newRouteCarsDefinedRouteDto.getCarId());
        if (chosenCar == null) throw new EntityNotFoundException(Car.class);

        DefinedRoute chosenDefinedRoute = definedRouteDaoInterface.getById(newRouteCarsDefinedRouteDto.getDefinedRouteId());
        if (chosenDefinedRoute == null) throw new EntityNotFoundException(DefinedRoute.class);

        newRoute.setDate((Date) newRouteCarsDefinedRouteDto.getDate());
        newRoute.setRouteType(chosenDefinedRoute);
        newRoute.setVehicle(chosenCar);

        routeDao.add(newRoute);
    }

    @Override
    public void update(Route cp) {
        routeDao.update(cp);
    }

    @Override
    public void delete(Long id) {
        routeDao.delete(id);
    }

    @Override
    public boolean checkIfEntityWithIdExists(Long id) {
        return routeDao.getById(id) != null;
    }
}
