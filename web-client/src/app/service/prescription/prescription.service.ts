import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {User} from '../../model/user.model';
import {environment} from '../../../environments/environment';
import {ApiConstants} from '../../../configs/api-constants';
import {Prescription} from "../../model/prescription.model";
import {Observable} from "rxjs";
import {PrescriptionFilter} from "../../model/prescription.filter";

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable()
export class PrescriptionService {

  prescriptionList: Prescription[] = [];

  constructor(private http: HttpClient) { }
  baseUrl: string = environment.apiBaseUrl + ApiConstants.SEPARATOR + ApiConstants.PRESCRIPTIONS;




  findById(id: string): Observable<Prescription> {
    const url = `${this.baseUrl}/${id}`;
    const params = { id };
    return this.http.get<Prescription>(url, {params, headers});
  }

  getPrescriptions() :Observable<HttpResponse<Prescription[]>> {
    return this.http.get<Prescription[]>(this.baseUrl,{observe: 'response'});
  }

  load(filter: PrescriptionFilter): void {
    this.find(filter).subscribe(result => {
        this.prescriptionList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: PrescriptionFilter): Observable<Prescription[]> {
    const params = {
      title: filter.title,
    };
    const prescriptions = 'http://localhost:8080/prescriptions';
    return this.http.get<Prescription[]>(prescriptions, {params, headers});
  }

  save(entity: Prescription): Observable<Prescription> {
    let params = new HttpParams();
    let url = '';
    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Prescription>(url, entity, {headers, params});
    } else {
      url = `${this.baseUrl}`;
      return this.http.post<Prescription>(url, entity, {headers, params});
    }
  }

  delete(entity: Prescription): Observable<Prescription> {
    let params = new HttpParams();
    let url = '';
    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Prescription>(url, {headers, params});
    }
    return null;
  }

  createPrescription(prescription: Prescription) {
    return this.http.post(this.baseUrl, prescription);
  }

  updatePrescription(prescription: Prescription) {
    return this.http.put(this.baseUrl + '/' + prescription.id, prescription);
  }

  deletePrescription(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
