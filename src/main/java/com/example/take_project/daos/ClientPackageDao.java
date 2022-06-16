package com.example.take_project.daos;

import com.example.take_project.models.Client;
import com.example.take_project.models.ClientPackage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class ClientPackageDao implements ClientPackageDaoInterface {
    @PersistenceContext(name = "default")
    EntityManager entityManager;

    @Override
    public void add(ClientPackage entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<ClientPackage> getAllOwnedBy(Client owner)
    {
        Query query = entityManager.createQuery("select cp from ClientPackage cp where cp.packageOwner = :owner");
        query.setParameter("owner", owner);
        return query.getResultList();
    }

    @Override
    public ClientPackage getById(Long key) {
        return entityManager.find(ClientPackage.class, key);
    }

    @Override
    public List<ClientPackage> getAll() {
        Query query = entityManager.createQuery("select cp from ClientPackage cp");
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        ClientPackage cp = getById(id);
        entityManager.remove(cp);
    }

    @Override
    public void update(ClientPackage entity) {
        entityManager.merge(entity);
    }
}
