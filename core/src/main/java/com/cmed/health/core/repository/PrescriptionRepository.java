package com.cmed.health.core.repository;

import com.cmed.health.core.entity.Prescription;
import com.cmed.health.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {


}
