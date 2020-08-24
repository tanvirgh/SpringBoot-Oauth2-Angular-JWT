package com.cmed.health.core.service;

import com.cmed.health.core.dto.PrescriptionDto;
import com.cmed.health.core.entity.Prescription;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

public interface PrescriptionService<S extends PrescriptionDto> extends BaseService<Prescription, S> {

    Optional<Prescription> findById(Long id);

    Collection<S> findByPrescriptionDate(Date date, Class<S> dtoClass);




}
