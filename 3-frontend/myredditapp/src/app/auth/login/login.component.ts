import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { LoginRequestPayload } from './login-request.payload';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequestPayload: LoginRequestPayload;
  isError: boolean;
  registerSuccessMessage: string;

  constructor(private authService: AuthService, private router: Router,
              private activatedRoute: ActivatedRoute, private toastr: ToastrService) { 
    
      this.loginRequestPayload={
        "password": "",
        "username": ""
    }
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    this.activatedRoute.queryParams.subscribe(
      params => {
        if(params.registered !== undefined && params.registered === true){
          this.toastr.success('Sign Up Successful');
          this.registerSuccessMessage= 'Please Check your inbox for activation email '
          + 'activate your account before you Login!';

        }
      }
    );
  }

  login(){
    this.loginRequestPayload.password=this.loginForm.get("password").value;
    this.loginRequestPayload.username=this.loginForm.get("username").value;

    console.log('Niti---> Inside login comp: login()');

    this.authService.login(this.loginRequestPayload).subscribe(
      data => {
        if(data){
          this.isError= false;
          // niti commented
          //this.router.navigateByUrl('/');
          this.toastr.success('Login Successful');
          console.log('Login Successful.');

          // Niti added to refresh page after login 
          this.router.navigateByUrl('').then(() => {
            window.location.reload();
          });
        }
        else {
          this.isError = true;
          console.log('Login Failed.');
        }
      }
    );
  }
}