package com.example.take_project.services;

import com.example.take_project.daos.CarDaoInterface;
import com.example.take_project.models.Car;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class CarService implements CarServiceInterface{
    @EJB
    CarDaoInterface carDao;

    @Override
    public void addNew(Car newCar) {
        carDao.add(newCar);
    }

    @Override
    public Car getById(Long id) {
        return carDao.getById(id);
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public void delete(Long id) {
        carDao.delete(id);
    }

    @Override
    public void update(Car car) {
        carDao.update(car);
    }
}
