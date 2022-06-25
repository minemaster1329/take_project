package com.example.take_project.daos;

import com.example.take_project.models.DefinedRoute;
import jakarta.ejb.Stateless;

@Stateless
public class DefinedRouteDao extends BasicCRUDDaoAbstract<DefinedRoute>
        implements DefinedRouteDaoInterface{

    public DefinedRouteDao() {
        super(DefinedRoute.class);
    }
}
