package com.cmed.health.core.service.impl;

import com.cmed.health.core.dto.PrescriptionDto;
import com.cmed.health.core.entity.Prescription;
import com.cmed.health.core.enums.Gender;
import com.cmed.health.core.repository.PrescriptionRepository;
import com.cmed.health.core.repository.UserRepository;
import com.cmed.health.core.service.PrescriptionService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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

        return prescriptionRepository.findById(id);
    }

    @Override
    public Collection<S> findByPrescriptionDate(Date date, Class<S> dtoClass) {
        Collection<Prescription> prescriptions = prescriptionRepository.findByPrescriptionDate(date);
        return prescriptions.isEmpty() ? Collections.emptyList() :
                prescriptions
                        .stream()
                        .map(prescription -> mapper.map(prescription, dtoClass))
                        .collect(Collectors.toList());
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
        try {
            Prescription prescription = mapper.map(dto, Prescription.class);
            prescription = prescriptionRepository.save(prescription);
            return Optional.of((S) mapper.map(prescription, PrescriptionDto.class));
        } catch (DataIntegrityViolationException ex) {
            //TODO: log error
        }
        return Optional.empty();
    }

    @Override
    public Optional<S> update(Long id, S dto) {

        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        Prescription prescription = prescriptionOptional.get();
        prescription.setPatientName(dto.getPatientName());
        prescription.setAge(dto.getAge());
        prescription.setPrescriptionDate(dto.getPrescriptionDate());
        prescription.setDiagnosis(dto.getDiagnosis());
        prescription.setGender(Gender.findByName(dto.getGender()));
        prescription.setMedicine(dto.getMedicine());
        prescription.setNextVisitDate(dto.getNextVisitDate());
        Prescription newPrescription = prescriptionRepository.save(prescription);
        return Optional.of((S) mapper.map(newPrescription, PrescriptionDto.class));
    }

    @Override
    public void remove(Long id) {
        Optional<Prescription> presOptional = prescriptionRepository.findById(id);
        presOptional.ifPresent(prescription -> prescriptionRepository.delete(prescription));
    }
}
