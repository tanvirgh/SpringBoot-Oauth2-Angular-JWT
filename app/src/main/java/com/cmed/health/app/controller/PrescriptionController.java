package com.cmed.health.app.controller;

import com.cmed.health.app.api.ApiConstants;
import com.cmed.health.app.api.ApiProvider;
import com.cmed.health.app.util.ResponseMaker;
import com.cmed.health.core.dto.PrescriptionDto;
import com.cmed.health.core.service.PrescriptionService;
import com.cmed.health.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@RestController
@RequestMapping(path = ApiProvider.PrescriptionApi.ROOT_PATH)
public class PrescriptionController {

    private PrescriptionService prescriptionService;
    private ResponseMaker responseMaker;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, ResponseMaker responseMaker) {
        this.prescriptionService = prescriptionService;
        this.responseMaker = responseMaker;
    }

    @PostMapping
    public ResponseEntity<PrescriptionDto> create(@RequestBody PrescriptionDto prescriptionDto) {
        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.persist(prescriptionDto);
        return responseMaker.responseForPost(prescriptionDtoOptional);
    }

    @PutMapping(value = ApiProvider.PrescriptionApi.PRESCRIPTIONID, produces = "application/json")
    public ResponseEntity<PrescriptionDto> updatePrescription(@PathVariable(name = ApiConstants.PRESCRIPTION_ID) long id,
                                                              @RequestBody PrescriptionDto prescriptionDto) {
        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.update(id, prescriptionDto);
        return responseMaker.responseForPatch(prescriptionDtoOptional);

    }


    @GetMapping(value = ApiProvider.PrescriptionApi.PRESCRIPTIONID, produces = "application/json")
    public ResponseEntity<PrescriptionDto> get(@PathVariable(name = ApiConstants.PRESCRIPTION_ID) long id) {

        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.findById(id, PrescriptionDto.class);
        return responseMaker.responseForGet(prescriptionDtoOptional);
    }

    @GetMapping
    public Collection<PrescriptionDto> getAll() {

        Date today = DateUtils.parseDate(DateUtils.format(new Date()), "yyyy-MM-dd");

        return prescriptionService.findByPrescriptionDate(today, PrescriptionDto.class);
    }

    @DeleteMapping(ApiProvider.PrescriptionApi.PRESCRIPTIONID)
    @ResponseStatus(code = HttpStatus.OK)
    public void deletePrescription(@PathVariable(name = ApiConstants.PRESCRIPTION_ID) long id) {
        prescriptionService.remove(id);
    }


    @GetMapping("/filter")
    public Collection<PrescriptionDto> getFilteredData(@RequestParam("date") String date) {

        Date prescriptionDate = DateUtils.parseDate(date, "yyyy-MM-dd");

        return prescriptionService.findByPrescriptionDate(prescriptionDate, PrescriptionDto.class);
    }






}
