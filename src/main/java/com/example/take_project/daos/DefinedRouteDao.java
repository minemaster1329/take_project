package com.example.take_project.daos;

import com.example.take_project.models.DefinedRoute;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class DefinedRouteDao implements DefinedRouteDaoInterface{
    @PersistenceContext(name="default")
    EntityManager entityManager;

    @Override
    public void add(DefinedRoute entity) {
        entityManager.persist(entity);
    }

    @Override
    public DefinedRoute getById(Long key) {
        return entityManager.find(DefinedRoute.class, key);
    }

    @Override
    public List<DefinedRoute> getAll() {
        Query query = entityManager.createQuery("select dr from DefinedRoute");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        DefinedRoute definedRoute = getById(id);
        entityManager.remove(definedRoute);
    }

    @Override
    public void update(DefinedRoute entity) {
        entityManager.merge(entity);
    }
}
