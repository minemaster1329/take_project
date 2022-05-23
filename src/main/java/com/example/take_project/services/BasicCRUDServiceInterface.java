package com.example.take_project.services;

import java.util.List;

public interface BasicCRUDServiceInterface<T> {
    T getById(Long id);
    List<T> getAll();
    void addNew(T cp);
    void update(T cp);
    void delete(Long id);
}
