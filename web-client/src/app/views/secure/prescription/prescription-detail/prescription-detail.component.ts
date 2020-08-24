import {Component, OnInit} from '@angular/core';
import {PrescriptionService} from "../../../../service/prescription/prescription.service";
import {Prescription} from "../../../../model/prescription.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-prescription-detail',
  templateUrl: './prescription-detail.component.html',
  styleUrls: ['./prescription-detail.component.css']
})
export class PrescriptionDetailComponent implements OnInit {

  constructor(private prescriptionService:PrescriptionService) { }

  prescription : Prescription=new Prescription();
  submitted = false;

  ngOnInit() {
    this.submitted=false;
  }

  prescriptionsaveform=new FormGroup({
    patientName:new FormControl('' , [Validators.required , Validators.minLength(5) ] ),
    age:new FormControl('',[Validators.required,Validators.email]),
    gender:new FormControl('',[Validators.required,Validators.email])

  });

  savePrescription(savePrescription){
    this.prescription=new Prescription();
    this.prescription.patientName=this.PatientName.value;
    this.prescription.age=this.Age.value;
    this.prescription.gender=this.Gender.value;
    this.submitted = true;
    this.save();
  }



  save() {
    this.prescriptionService.savePrescription(this.prescription)
      .subscribe(data => console.log(data), error => console.log(error));
    this.prescription = new Prescription();
  }

  get PatientName(){
    return this.prescriptionsaveform.get('patient_name');
  }

  get Age(){
    return this.prescriptionsaveform.get('age');
  }

  get Gender(){
    return this.prescriptionsaveform.get('gender');
  }

  addStudentForm(){
    this.submitted=false;
    this.prescriptionsaveform.reset();
  }

}
