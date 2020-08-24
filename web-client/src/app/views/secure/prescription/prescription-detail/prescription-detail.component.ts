import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-prescription-detail',
  templateUrl: './prescription-detail.component.html',
  styleUrls: ['./prescription-detail.component.css']
})
export class PrescriptionDetailComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<PrescriptionDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public prescriptionForm: FormGroup) { }

  ngOnInit() {
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
