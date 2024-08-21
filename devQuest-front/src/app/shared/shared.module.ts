import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegisterComponent } from './components/register/register.component';
import { RouterLink, RouterModule } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { DarkModeToggleComponent } from './components/dark-mode-toggle/dark-mode-toggle.component';

@NgModule({
  declarations: [
    LoginPageComponent,
    RegisterComponent,
    DarkModeToggleComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    RouterLink,
    MaterialModule,
    ReactiveFormsModule,
  ],
  providers: [provideHttpClient()],
  exports: [DarkModeToggleComponent],
})
export class SharedModule {}
