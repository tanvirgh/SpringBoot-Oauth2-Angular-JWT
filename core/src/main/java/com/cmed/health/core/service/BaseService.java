package com.cmed.health.core.service;

import com.cmed.health.core.dto.BaseDto;
import com.cmed.health.core.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */
public interface BaseService<T extends BaseEntity, S extends BaseDto> {

    Optional<S> findById(Long id, Class<S> dtoClass);

    Collection<S> getAll(Class<S> dtoClass);

    Optional<S> persist(S dto);

    Optional<S> update(Long id, S dto);

    void remove(Long id);

    /*void removeAll();*/
}
