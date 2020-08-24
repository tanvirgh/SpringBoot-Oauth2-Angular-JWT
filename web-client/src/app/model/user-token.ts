export class UserToken {

  constructor(private _token: string, private _username: string, private _userId, private _isAdmin:boolean,
              private _isManager: boolean, private _isLead: boolean) {

  }

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get userId() {
    return this._userId;
  }

  set userId(value) {
    this._userId = value;
  }

  get isAdmin(): boolean {
    return this._isAdmin;
  }

  set isAdmin(value: boolean) {
    this._isAdmin = value;
  }

  get isManager(): boolean {
    return this._isManager;
  }

  set isManager(value: boolean) {
    this._isManager = value;
  }

  get isLead(): boolean {
    return this._isLead;
  }

  set isLead(value: boolean) {
    this._isLead = value;
  }

}
