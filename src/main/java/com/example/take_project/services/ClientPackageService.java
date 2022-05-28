package com.example.take_project.services;

import com.example.take_project.daos.ClientPackageDaoInterface;
import com.example.take_project.models.ClientPackage;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class ClientPackageService implements ClientPackageServiceInterface{
    @EJB
    ClientPackageDaoInterface clientPackageDao;

    @Override
    public ClientPackage getById(Long id) {
        return clientPackageDao.getById(id);
    }

    @Override
    public List<ClientPackage> getAll() {
        return clientPackageDao.getAll();
    }

    @Override
    public void addNew(ClientPackage cp) {
        clientPackageDao.add(cp);
    }

    @Override
    public void update(ClientPackage cp) {
        clientPackageDao.update(cp);
    }

    @Override
    public void delete(Long id) {
        clientPackageDao.delete(id);
    }

    @Override
    public boolean checkIfEntityWithIdExists(Long id) {
        return clientPackageDao.getById(id) != null;
    }
}
