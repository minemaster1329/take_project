package com.example.take_project.services;

import com.example.take_project.daos.RouteDao;
import com.example.take_project.daos.RouteDaoInterface;
import com.example.take_project.models.Route;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class RouteService implements RouteServiceInterface{
    @EJB
    RouteDaoInterface routeDao;

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
}
