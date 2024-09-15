import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegisterComponent } from './components/register/register.component';
import { RouterLink, RouterModule } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { DarkModeToggleComponent } from './components/dark-mode-toggle/dark-mode-toggle.component';
import { BasicNavComponent } from './components/basic-nav/basic-nav.component';
import { LandingComponent } from './components/landing/landing.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { TermsComponent } from './components/terms/terms.component';

@NgModule({
  declarations: [
    LoginPageComponent,
    RegisterComponent,
    DarkModeToggleComponent,
    BasicNavComponent,
    LandingComponent,
    FooterComponent,
    AboutComponent,
    ContactComponent,
    TermsComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    RouterLink,
    MaterialModule,
    ReactiveFormsModule,
  ],
  providers: [provideHttpClient()],
  exports: [DarkModeToggleComponent, FooterComponent],
})
export class SharedModule {}
