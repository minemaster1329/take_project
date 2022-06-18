package com.example.take_project.services;

import com.example.take_project.daos.CarDaoInterface;
import com.example.take_project.daos.RouteDaoInterface;
import com.example.take_project.models.Car;
import com.example.take_project.models.DefinedRoute;
import com.example.take_project.models.Route;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class RouteService implements RouteServiceInterface{
    @EJB
    RouteDaoInterface routeDao;
    @EJB
    CarDaoInterface carDaoInterface;

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

    @Override
    public List<Route> getRoutesForCarInSpecifiedDay(Long carId, Date date) throws EntityNotFoundException {
        if (carId == null) throw new IllegalArgumentException("Car ID cannot be null");
        if (carDaoInterface.getById(carId) == null) throw new EntityNotFoundException(Car.class);
        if (date == null){
            return routeDao.getAll().stream().filter(route -> route.getVehicle().getId().equals(carId)).collect(Collectors.toList());
        }
        else return routeDao.getAll().stream().filter(route -> route.getVehicle().getId().equals(carId) && Objects.equals(route.getDate(), date)).collect(Collectors.toList());
    }
}
