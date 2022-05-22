package com.example.take_project.daos;

import com.example.take_project.models.Client;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class ClientDao implements ClientDaoInterface{
    @PersistenceContext(name = "default")
    EntityManager entityManager;

    @Override
    public void add(Client entity) {
        entityManager.persist(entity);
    }

    @Override
    public Client getById(Long key) {
        return entityManager.find(Client.class, key);
    }

    @Override
    public List<Client> getAll() {
        Query query = entityManager.createQuery("select c from Client");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Client cli = entityManager.find(Client.class, id);
        entityManager.remove(cli);
    }

    @Override
    public void update(Client entity) {
        entityManager.merge(entity);
    }
}
