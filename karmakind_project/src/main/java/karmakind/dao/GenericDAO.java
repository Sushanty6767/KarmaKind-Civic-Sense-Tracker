package com.karmakind.dao;

import com.karmakind.util.DataAccessException;
import java.util.List;

public interface GenericDAO<T> {
    void save(T obj) throws DataAccessException;
    T findById(int id) throws DataAccessException;
    List<T> findAll() throws DataAccessException;
    void update(T obj) throws DataAccessException;
    void delete(int id) throws DataAccessException;
}
