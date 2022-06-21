package com.example.take_project.daos;

import com.example.take_project.models.Client;
import com.example.take_project.models.ClientPackage;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class ClientPackageDao extends BasicCRUDDaoAbstract<ClientPackage>
        implements ClientPackageDaoInterface {

    public ClientPackageDao() {
        super(ClientPackage.class);
    }

    @Override
    public List<ClientPackage> getAllOwnedBy(Client owner)
    {
        Query query = manager.createQuery("select cp from ClientPackage cp where cp.packageOwner = :owner");
        query.setParameter("owner", owner);
        return query.getResultList();
    }

}
