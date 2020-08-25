import {HttpResponse} from "@angular/common/http";
import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {PrescriptionFilter} from "../../../model/prescription.filter";
import {Prescription} from "../../../model/prescription.model";
import {PrescriptionService} from "../../../service/prescription/prescription.service";
import {PrescriptionDetailComponent} from './prescription-detail/prescription-detail.component';
import {fromEvent} from "rxjs/internal/observable/fromEvent";
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';

@Component({
  selector: 'app-prescription',
  templateUrl: './prescription.component.html',
  styleUrls: ['./prescription.component.css']
})
export class PrescriptionComponent implements OnInit {

  prescriptionList :Prescription[];
  selectedPrescription: Prescription;

  filter = new PrescriptionFilter();
  feedback: any = {};


  constructor(private prescriptionService : PrescriptionService, public dialog: MatDialog) { }

  ngOnInit() {
    this.getPrescriptionList();
    
    const searchBox = document.getElementById('search-box');
    fromEvent(searchBox, 'input').pipe(
      map((e: KeyboardEvent) => (e.target as HTMLInputElement).value),
      filter(text => text.length > 2),
      debounceTime(10),
      distinctUntilChanged()
    ).subscribe(data => {
      console.log('searched:', data);
    });
  }

  select(selected: Prescription): void {
    this.selectedPrescription = selected;
  }

  search(): void {
    this.prescriptionService.load(this.filter);
  }

  getPrescriptionList() {
    this.prescriptionService.getPrescriptions().subscribe((res: HttpResponse<Prescription[]>) => {
      this.prescriptionList = res.body;
    });
  }

  delete(prescription: Prescription): void {
    if (confirm('Are you sure?')) {
      this.prescriptionService.delete(prescription).subscribe(() => {
          this.prescriptionList.splice(this.prescriptionList.indexOf(prescription), 1);
          this.feedback = {type: 'success', message: 'Delete was successful!'};
          setTimeout(() => {
            this.search();
          }, 1000);
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      );
    }
  }

  createPrescriptionForm(prescription?: Prescription):FormGroup {
    const tmpPrescription = prescription ? prescription: new Prescription();
    const prescriptionform = new FormGroup({
      id: new FormControl(tmpPrescription.id),
      patientName:new FormControl(tmpPrescription.patientName , [Validators.required , Validators.minLength(5) ] ),
      age:new FormControl(tmpPrescription.age,[Validators.required,Validators.email]),
      gender: new FormControl(tmpPrescription.gender, [Validators.required, Validators.email]),
      prescriptionDate: new FormControl(tmpPrescription.prescriptionDate, [Validators.required, Validators.email]),
      diagnosis: new FormControl(tmpPrescription.diagnosis, [Validators.required, Validators.email]),
      medicine: new FormControl(tmpPrescription.medicine, [Validators.required, Validators.email]),
      nextVisitDate: new FormControl(tmpPrescription.nextVisitDate, [Validators.required, Validators.email])
    });
    return prescriptionform;
  }

  editPrescription(prescription: Prescription) {

    const prescriptionForm = this.createPrescriptionForm(prescription);

    const dialogRef = this.dialog.open(PrescriptionDetailComponent, {
      width: 'auto',
      data: prescriptionForm
    });

    dialogRef.afterClosed().subscribe(editedPrescription => {
      console.log(editedPrescription);
      if(editedPrescription) {
        this.prescriptionService.updatePrescription(editedPrescription).subscribe(prescriptionData => {
          console.log('prescription edited data arrived ', prescriptionData);
          this.prescriptionList = this.prescriptionList.map(prescription => prescription.id === prescriptionData.id ? prescriptionData : prescription);
        });
      }
    });

  }

  addPrescription() {
    const prescriptionForm = this.createPrescriptionForm();

    const dialogRef = this.dialog.open(PrescriptionDetailComponent, {
      width: 'auto',
      data: prescriptionForm
    });

    dialogRef.afterClosed().subscribe(newPrescription => {
      console.log(newPrescription);
      if(newPrescription) {
        this.prescriptionService.createPrescription(newPrescription).subscribe(prescriptionData => {
          console.log('prescription edited data arrived ', prescriptionData);
          this.prescriptionList.unshift(prescriptionData);
        });
      }
    });
  }
}
