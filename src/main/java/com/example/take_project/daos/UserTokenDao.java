package com.example.take_project.daos;

import com.example.take_project.models.User;
import com.example.take_project.models.UserToken;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class UserTokenDao implements UserTokenDaoInterface{
    @PersistenceContext(name="default")
    EntityManager entityManager;

    @Override
    public void add(UserToken entity) {
        entityManager.persist(entity);
    }

    @Override
    public UserToken getById(Long key) {
        return entityManager.find(UserToken.class, key);
    }

    @Override
    public List<UserToken> getAll() {
        Query query = entityManager.createQuery("select u from UserToken u");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        UserToken userToken = getById(id);
        entityManager.remove(userToken);
    }

    @Override
    public void update(UserToken entity) {
        entityManager.merge(entity);
    }
}
