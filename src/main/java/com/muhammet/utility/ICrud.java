package com.muhammet.utility;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICrud<T,ID> {

    T save(T entiy);
    Iterable<T>  saveAll(Iterable<T> entites);
    void delete(T entity);
    void deleteById(ID id);
    Optional<T> findById(ID id);
    boolean existById(ID id);
    List<T> findAll();
    List<T> findByEntity(T entity);
    List<T> findByColumnNameAndValue(String columnName, String value);
//    List<T> findByColumnNameAndValue(String columnName, Long value);
//    List<T> findByColumnNameAndValue(String columnName, BigDecimal value);



}
