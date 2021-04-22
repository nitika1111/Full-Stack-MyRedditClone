import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import {NgxWebstorageModule} from 'ngx-webstorage';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent, 
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot()  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
