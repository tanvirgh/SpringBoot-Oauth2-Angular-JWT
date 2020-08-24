package com.cmed.health.core.dto;

import com.cmed.health.core.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDto extends BaseDto{


    private Date prescriptionDate = new Date();


    private String patientName;


    private int age;


    private Gender gender;



    private String diagnosis;



    private String medicine;


    private Date nextVisitDate = new Date();
}
