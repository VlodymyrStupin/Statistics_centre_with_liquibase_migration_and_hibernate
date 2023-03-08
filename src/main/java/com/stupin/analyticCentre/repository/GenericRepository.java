package ua.stupin.analyticCentre.repository;

import java.util.List;

public interface GenericRepository<T> {
    T getById(String id);

    List<T> getAll();

    void save(T value);
}
