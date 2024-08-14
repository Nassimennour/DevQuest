import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegisterComponent } from './components/register/register.component';
import { RouterLink, RouterModule } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [LoginPageComponent, RegisterComponent],
  imports: [
    CommonModule,
    RouterModule,
    RouterLink,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class SharedModule {}
