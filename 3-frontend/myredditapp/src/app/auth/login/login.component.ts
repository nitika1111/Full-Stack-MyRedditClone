import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { LoginRequestPayload } from './login-request.payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequestPayload: LoginRequestPayload;
  isError: boolean;

  constructor(private authService: AuthService, private router: Router) { 
    this.loginRequestPayload={
      "password":"",
      "username":""
    }
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  login(){
    this.loginRequestPayload.password=this.loginForm.get("password").value;
    this.loginRequestPayload.username=this.loginForm.get("username").value;

    this.authService.login(this.loginRequestPayload).subscribe(
      data => {
        console.log('Login Successful');
        this.isError = false;
      }
    );
  }
}