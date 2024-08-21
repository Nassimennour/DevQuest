import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationRequest } from '../../models/usermodels';
import { firstValueFrom } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  @ViewChild('toast') toast!: ElementRef;

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private router: Router) {}

  togglePassword($event: MouseEvent) {
    $event.preventDefault();
    const passwordField = document.querySelector('#password');
    if (passwordField?.getAttribute('type') === 'password') {
      passwordField.setAttribute('type', 'text');
    } else {
      passwordField?.setAttribute('type', 'password');
    }
  }

  async loginUser() {
    const loginData: AuthenticationRequest = {
      username: this.loginForm.get('username')?.value,
      password: this.loginForm.get('password')?.value,
    };
    console.log('Login data: ', loginData);

    try {
      const response = await firstValueFrom(
        this.authService.loginUser(loginData)
      );
      console.log('User logged in successfully');
      switch (this.authService.userRole) {
        case 'ROLE_USER':
          this.router.navigate(['/user']);
          break;
        case 'ROLE_ADMIN':
          this.router.navigate(['/admin']);
          break;
      }
    } catch (error) {
      console.error(error);
    }
  }
}
