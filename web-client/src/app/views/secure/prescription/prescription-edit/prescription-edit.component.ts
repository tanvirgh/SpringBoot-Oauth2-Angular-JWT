import {Component, OnInit} from '@angular/core';
import {Prescription} from "../../../../model/prescription.model";
import {PrescriptionService} from "../../../../service/prescription/prescription.service";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-prescription-edit',
  templateUrl: './prescription-edit.component.html',
  styleUrls: ['./prescription-edit.component.css']
})
export class PrescriptionEditComponent implements OnInit {

  id: string;
  prescription: Prescription;
  feedback: any = {};
  editMode: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private prescriptionService : PrescriptionService) { }



  ngOnInit() {
    this.route.params.subscribe((params: Params) => {

      this.id = params['id'];
      this.prescriptionService.findById(this.id).subscribe(prescription => {
          this.prescription = prescription;
          // this.note.id = +note.id;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );

      this.editMode = params['id'] != null;

    })

  }

  save() {
    this.prescriptionService.savePrescription(this.prescription).subscribe(
      prescription => {
        this.prescription = prescription;
        // this.note.id = +this.id;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/prescriptions']);
        }, 1000);
      },
      err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    );
  }

  cancel() {
    this.router.navigate(['/prescriptions']);
  }

}
