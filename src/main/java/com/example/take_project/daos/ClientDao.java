package com.example.take_project.daos;

import com.example.take_project.models.Client;
import jakarta.ejb.Stateless;

@Stateless
public class ClientDao extends BasicCRUDDaoAbstract<Client> implements ClientDaoInterface{

    public ClientDao() {
        super(Client.class);
    }

}
