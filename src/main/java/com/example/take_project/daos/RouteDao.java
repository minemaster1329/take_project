package com.example.take_project.daos;

import com.example.take_project.models.Route;
import jakarta.ejb.Stateless;

@Stateless
public class RouteDao extends BasicCRUDDaoAbstract<Route>
        implements RouteDaoInterface {

    public RouteDao() {
        super(Route.class);
    }

}
