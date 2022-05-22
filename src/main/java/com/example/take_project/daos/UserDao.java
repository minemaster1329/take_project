package com.example.take_project.daos;

import com.example.take_project.models.Route;
import com.example.take_project.models.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class UserDao implements UserDaoInterface{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public User getById(Long key) {
        return entityManager.find(User.class, key);
    }

    @Override
    public List<User> getAll() {
        Query query = entityManager.createQuery("select u from User u");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        entityManager.remove(user);
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }
}
