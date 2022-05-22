package com.example.take_project.services;

import com.example.take_project.models.Car;

import java.util.List;

public interface CarServiceInterface {
    void add(Car newCar);
    Car getById(Long id);
    List<Car> getAll();
    void delete(Long id);
    void update(Car car);
}
