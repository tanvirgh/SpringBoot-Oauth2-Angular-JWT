import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, MatCardModule,
  MatDialogModule, MatInputModule,
  MatTableModule, MatCheckboxModule,
  MatToolbarModule
} from '@angular/material';

@NgModule({
  imports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule, MatDialogModule,
    MatTableModule, MatCheckboxModule],
  exports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule, MatDialogModule,
    MatTableModule, MatCheckboxModule],
})
export class CustomMaterialModule { }
