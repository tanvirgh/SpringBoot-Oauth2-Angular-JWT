import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../service/auth/auth.service';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading: boolean;

  constructor(private router: Router, private formBuilder: FormBuilder, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.clearUserSessionData();
    this.loading = this.authenticationService.isLoggedin();
    this.loginForm = this.formBuilder.group({
          username: [null, Validators.required],
          password: [null, Validators.required]
        }
    );
    console.log('on init, loading configs from: ' + environment.name);
  }

  onSubmit() {
    this.loading = true;
    this.authenticationService.login(this.loginForm.value)
        .subscribe(result => {
          if (result.isAdmin) { localStorage.setItem('is-user_admin', 'yes'); }
          if (result.isManager) { localStorage.setItem('is-user_manager', 'yes'); }
          if (result.isLead) { localStorage.setItem('is-user_lead', 'yes'); }
          /*else
            localStorage.setItem("is-user_admin",'no');*/
          localStorage.setItem('user_token', result.token);
          localStorage.setItem('user_name', result.username);
          localStorage.setItem('user_id', result.userId);
          this.router.navigate(['/prescriptions']);
        }, error => {
            alert('Username And psassword does not match.');
            console.log('some problems occurred while you trying to login');
        });
  }

}
