package com.example.take_project.daos;

import com.example.take_project.models.Car;
import jakarta.ejb.Stateless;


@Stateless
public class CarDao extends BasicCRUDDaoAbstract<Car> implements CarDaoInterface {

    public CarDao() {
        super(Car.class);
    }

}
