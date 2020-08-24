import { Component, OnInit } from '@angular/core';
import {PrescriptionService} from "../../../service/prescription/prescription.service";
import {Prescription} from "../../../model/prescription.model";
import {HttpResponse} from "@angular/common/http";
import {PrescriptionFilter} from "../../../model/prescription.filter";

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

  constructor(private prescriptionService : PrescriptionService) { }

  ngOnInit() {
    this.getPrescriptionList();
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


}
