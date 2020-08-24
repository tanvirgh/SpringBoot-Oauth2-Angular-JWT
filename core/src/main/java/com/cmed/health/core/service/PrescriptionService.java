package com.cmed.health.core.service;

import com.cmed.health.core.dto.PrescriptionDto;
import com.cmed.health.core.dto.UserDto;
import com.cmed.health.core.entity.Prescription;
import com.cmed.health.core.entity.User;

import java.util.Optional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

public interface PrescriptionService<S extends PrescriptionDto> extends BaseService<Prescription, S> {

    Optional<Prescription> findById(Long id);




}
