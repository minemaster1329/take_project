package com.example.take_project.daos;

import com.example.take_project.models.DefinedRoute;
import com.example.take_project.models.Route;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class RouteDao implements RouteDaoInterface {
    @PersistenceContext(name="default")
    EntityManager entityManager;

    @Override
    public void add(Route entity) {
        entityManager.persist(entity);
    }

    @Override
    public Route getById(Long key) {
        return entityManager.find(Route.class, key);
    }

    @Override
    public List<Route> getAll() {
        Query query = entityManager.createQuery("select r from Route r");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Route route = getById(id);
        entityManager.remove(route);
    }

    @Override
    public void update(Route entity) {
        entityManager.merge(entity);
    }
}
