package com.example.take_project.daos;

import com.example.take_project.models.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BasicCRUDDaoAbstract<T> {
    private Class<T> type2;
    @PersistenceContext(name="default")
    EntityManager entityManager;

    BasicCRUDDaoAbstract(){
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type2 = (Class) pt.getActualTypeArguments()[0];
    }

    public void add(T entity){
        entityManager.persist(entity);
    }

    public T getById(Long key){
        return entityManager.find(type2, key);
    }

    public List<T> getAll(){
        Query query = entityManager.createQuery(String.format("select c from %s c", type2.getName()));
        return query.getResultList();
    }

    public void delete(Long id){
        T entity = entityManager.find(type2 ,id);
        entityManager.remove(entity);
    }

    public void update(T entity){
        entityManager.merge(entity);
    }
}
