package com.cmed.health.core.repository;

import com.cmed.health.core.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>, CrudRepository<Prescription, Long> {


    Collection<Prescription> findByPrescriptionDate(Date date);
}
