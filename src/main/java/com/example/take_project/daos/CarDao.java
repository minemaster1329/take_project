package com.example.take_project.daos;

import com.example.take_project.models.Car;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class CarDao implements BasicCRUDDaoInterface<Car> {
    @PersistenceContext(name = "default")
    EntityManager entityManager;
    @Override
    public void add(Car entity) {
        entityManager.persist(entity);
    }

    @Override
    public Car getById(Long key) {
        return entityManager.find(Car.class, key);
    }

    @Override
    public List<Car> getAll() {
        Query query = entityManager.createQuery("select c from Car");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Car car = entityManager.find(Car.class ,id);
        entityManager.remove(car);
    }

    @Override
    public void update(Car entity) {
        entityManager.merge(entity);
    }
}
