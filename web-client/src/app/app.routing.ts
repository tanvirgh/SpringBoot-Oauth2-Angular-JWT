import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './views/login/login.component';

import {AddUserComponent} from './views/secure/user/add-user/add-user.component';
import {HomeComponent} from './views/secure/home/home.component';

import {AuthGuard} from './service/auth/auth.guard';
import {PrescriptionComponent} from "./views/secure/prescription/prescription.component";
import {PrescriptionEditComponent} from "./views/secure/prescription/prescription-edit/prescription-edit.component";
import {PrescriptionDetailComponent} from "./views/secure/prescription/prescription-detail/prescription-detail.component";


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: 'home', component: HomeComponent, canActivate: [AuthGuard], children: [
      {path: '', component: HomeComponent, pathMatch: 'full'}
    ]
  },

  { path: 'prescriptions', component: PrescriptionComponent,canActivate: [AuthGuard], children: [
      { path: '', component: PrescriptionComponent },
      { path: 'new', component: PrescriptionEditComponent },
      { path: ':id', component: PrescriptionDetailComponent },
      { path: ':id/edit', component: PrescriptionEditComponent }
    ]},

  // {
  //   path: 'prescriptions', component: PrescriptionComponent,canActivate: [AuthGuard], children: [
  //     {path: '', redirectTo: 'list', pathMatch: 'full'}
  //   ]
  // },

  {
    path: 'users', canActivate: [AuthGuard], children: [
      {path: '', redirectTo: 'list', pathMatch: 'full'}
    ]
  },
  // {path: 'users', component: ListUserComponent},



  {path: 'add-user', component: AddUserComponent},


  // { path: '', redirectTo: 'home', pathMatch: 'full', canActivate: [AuthGuard] },
   { path: '**', redirectTo: 'login', canActivate: [AuthGuard] },
];

export const routing = RouterModule.forRoot(routes);
