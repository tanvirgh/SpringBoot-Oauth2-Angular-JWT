import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { LoginComponent } from './views/login/login.component';
import {routing} from './app.routing';

import {AuthenticationService} from './service/auth/auth.service';
import {UserService} from './service/user/user.service';
import {PrescriptionService} from './service/prescription/prescription.service';
import { AddUserComponent } from './views/secure/user/add-user/add-user.component';
import {CustomMaterialModule} from './material.module';
import { HomeComponent } from './views/secure/home/home.component';

import {CustomCorsInterceptor} from './custom-cors-interceptor';
import {HTTP_INTERCEPTORS} from '@angular/common/http';

import {AdminGuard} from './service/auth/admin.guard';
import {AuthGuard} from './service/auth/auth.guard';

import { FormsModule } from '@angular/forms';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import {TreeTableModule} from 'ng-treetable';
import { PrescriptionComponent } from './views/secure/prescription/prescription.component';
import { PrescriptionEditComponent } from './views/secure/prescription/prescription-edit/prescription-edit.component';
import { PrescriptionDetailComponent } from './views/secure/prescription/prescription-detail/prescription-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddUserComponent,
    HomeComponent,
    PrescriptionComponent,
    PrescriptionEditComponent,
    PrescriptionDetailComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    TreeTableModule,
    AngularFontAwesomeModule
  ],
  providers: [AuthenticationService, AdminGuard, AuthGuard, UserService,PrescriptionService,
    { provide: HTTP_INTERCEPTORS, useClass: CustomCorsInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
