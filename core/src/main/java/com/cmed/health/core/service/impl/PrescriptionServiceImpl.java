package com.cmed.health.core.service.impl;

import com.cmed.health.core.dto.PrescriptionDto;
import com.cmed.health.core.entity.Prescription;
import com.cmed.health.core.repository.PrescriptionRepository;
import com.cmed.health.core.repository.UserRepository;
import com.cmed.health.core.service.PrescriptionService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Log4j2
@Service
public class PrescriptionServiceImpl<S extends PrescriptionDto> implements PrescriptionService<S> {


    private ModelMapper mapper;
    private UserRepository userRepository;
    private PrescriptionRepository prescriptionRepository;


    @Autowired
    public PrescriptionServiceImpl(ModelMapper mapper, UserRepository userRepository,
                                   PrescriptionRepository prescriptionRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.prescriptionRepository = prescriptionRepository;

    }

    @Override
    public Optional<Prescription> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<S> findById(Long id, Class<S> dtoClass) {
        return Optional.empty();
    }

    @Override
    public Collection<S> getAll(Class<S> dtoClass) {
        Collection<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptions.isEmpty() ? Collections.emptyList() :
                prescriptions
                        .stream()
                        .map(prescription -> mapper.map(prescription, dtoClass))
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<S> persist(S dto) {
        return Optional.empty();
    }
}
