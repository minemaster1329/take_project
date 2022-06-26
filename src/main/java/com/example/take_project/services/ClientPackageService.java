package com.example.take_project.services;

import com.example.take_project.daos.CarDaoInterface;
import com.example.take_project.daos.ClientDaoInterface;
import com.example.take_project.daos.ClientPackageDaoInterface;
import com.example.take_project.daos.RouteDaoInterface;
import com.example.take_project.dto.clientpackage.NewClientPackageDto;
import com.example.take_project.dto.clientpackage.UpdatedClientPackageDto;
import com.example.take_project.models.Car;
import com.example.take_project.models.Client;
import com.example.take_project.models.ClientPackage;
import com.example.take_project.models.Route;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;
import com.example.take_project.otherstuff.exceptions.InvalidUpdateEntityDataException;
import com.example.take_project.otherstuff.mappers.PackageDTOtoPackage;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ClientPackageService implements ClientPackageServiceInterface{
    @EJB
    ClientPackageDaoInterface clientPackageDao;
    @EJB
    ClientDaoInterface clientDaoInterface;
    @EJB
    RouteDaoInterface routeDaoInterface;

    @EJB
    CarDaoInterface carDaoInterface;

    @Override
    public ClientPackage getById(Long id) {
        return clientPackageDao.getById(id);
    }

    @Override
    public List<ClientPackage> getAll() {
        return clientPackageDao.getAll();
    }

    @Override
    public List<ClientPackage> getAllOwnedByClient(Long ownerID)
            throws EntityNotFoundException {
        Client owner = clientDaoInterface.getById(ownerID);
        if(owner == null) throw new EntityNotFoundException(Client.class);
        return clientPackageDao.getAll().stream().filter(clientPackage -> clientPackage.getPackageOwner().getId().equals(ownerID)).collect(Collectors.toList());
    }

    @Override
    public List<ClientPackage> getPackagesForCarAndRoute(Long carId, Long routeId) throws EntityNotFoundException {
        Car c =  carDaoInterface.getById(carId);
        Route r = routeDaoInterface.getById(routeId);

        if (c == null) throw new EntityNotFoundException(Car.class);
        if (r == null) throw new EntityNotFoundException(Route.class);

        return clientPackageDao.getAll().stream().filter(clientPackage -> clientPackage.getRoute() != null && clientPackage.getRoute().getId().equals(routeId) && clientPackage.getRoute().getVehicle().getId().equals(carId)).collect(Collectors.toList());
    }

    @Override
    public void assignRouteToPackage(Long packageId, Long routeId) throws EntityNotFoundException {
        Route r = routeDaoInterface.getById(routeId);
        if (r == null) throw new EntityNotFoundException(Route.class);
        ClientPackage cp = clientPackageDao.getById(packageId);
        if (cp == null) throw new EntityNotFoundException(ClientPackage.class);

        cp.setRoute(r);
        clientPackageDao.update(cp);
    }


    @Override
    public void addNew(NewClientPackageDto cp) throws EntityNotFoundException {
        ClientPackage newClientPackage = new PackageDTOtoPackage().map(cp);

        Client packageClient = clientDaoInterface.getById(cp.getPackageOwnerId());
        if (packageClient == null) throw new EntityNotFoundException(Client.class);

        newClientPackage.setPackageOwner(packageClient);

        if (cp.getPackageRouteId().isPresent()){
            Route packageRoute = routeDaoInterface.getById(cp.getPackageRouteId().get());
            if (packageRoute == null) throw new EntityNotFoundException(Route.class);
            packageRoute.addPackage(newClientPackage);
        }

        clientPackageDao.add(newClientPackage);
    }

    @Override
    public void update(UpdatedClientPackageDto cp) throws InvalidUpdateEntityDataException, EntityNotFoundException {
        ClientPackage clientPackage = clientPackageDao.getById(cp.getId());
        if (cp.getPackageOwnerId() == null) throw new InvalidUpdateEntityDataException("Supplied updated version of client package with no id");
        if (clientPackage.getPackageOwner().getId() != cp.getPackageOwnerId()){
            Client client = clientDaoInterface.getById(cp.getId());
            clientPackage.setPackageOwner(client);
        }

        if (cp.getPackageRouteId().isPresent()){
            Route rt = routeDaoInterface.getById(cp.getPackageRouteId().get());
            if (rt == null) throw new EntityNotFoundException(Route.class);
            clientPackage.setRoute(rt);
        }
        else clientPackage.setRoute(null);

        clientPackage.setType(cp.getType());
        clientPackage.setPrice(cp.getPrice());
        clientPackage.setPaidFor(cp.getPaidFor());
        clientPackage.setDeliveryAddress(cp.getDeliveryAddress());
        clientPackage.setWeight(cp.getWeight());


        clientPackageDao.update(clientPackage);
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
