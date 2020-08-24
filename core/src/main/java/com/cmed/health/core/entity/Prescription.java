package com.cmed.health.core.entity;

import com.cmed.health.core.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription extends BaseEntity {

    @Column(name = "date_of_prescription", nullable = false)
    @Temporal(TemporalType.DATE)
    @Builder.Default
    private Date prescriptionDate = new Date();

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "patient_gender")
    @Convert(converter = Gender.TypeConverter.class)
    private Gender gender;


    @Column(name = "diagnosis_details", nullable = false)
    private String diagnosis;


    @Column(name = "medicine", nullable = false)
    private String medicine;

    @Column(name = "date_of_next_visit", nullable = false)
    @Temporal(TemporalType.DATE)
    @Builder.Default
    private Date nextVisitDate = new Date();


}
