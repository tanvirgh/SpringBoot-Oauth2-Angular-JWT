import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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
