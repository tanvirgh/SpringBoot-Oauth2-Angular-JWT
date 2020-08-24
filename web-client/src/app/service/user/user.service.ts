import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../../model/user.model';
import {environment} from '../../../environments/environment';
import {ApiConstants} from '../../../configs/api-constants';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }
  baseUrl: string = environment.apiBaseUrl + ApiConstants.SEPARATOR + ApiConstants.USERS;

  getUsers() {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUsersByOrganizationId(orgId: number) {
    console.log('getUersByOrganizations');
    return this.http.get<User[]>(this.baseUrl + '?orgId=2'); // change later for now only one organization - 'voin school'
                                                               // which id is 2. its ok with id 2;
  }

  getManagableUsersByOrganizationId(orgId: number) {
    return this.http.get<User[]>(this.baseUrl + '?orgId=2&role=LEAD'); // change later for now only one organization - 'voin school'
                                                                         // which id is 2. its ok with id 2;
  }

  getUserByOrganizationIdAndRole(orgId: number, roleName: string) {
    return this.http.get<User[]>(this.baseUrl + '?orgId=2&role=' + roleName); // change later for now only one organization - 'voin school'
                                                                              // which id is 2. its ok with id 2;
  }

  getUserByOrganizationIdAndRoleAndTeamLead(orgId: number, roleName: string, teamLeadId: string) {
    return this.http.get<User[]>(this.baseUrl + '?orgId=2&role=' + roleName + '&loggedInUserId=' + teamLeadId);
                                                            // change later for now only one organization - 'voin school'
                                                            // which id is 2. its ok with id 2;
  }

  getUserById(id: number) {
    return this.http.get<User>(this.baseUrl + '/' + id);
  }

  createUser(user: User) {
    return this.http.post(this.baseUrl, user);
  }

  updateUser(user: User) {
    return this.http.put(this.baseUrl + '/' + user.id, user);
  }

  deleteUser(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
