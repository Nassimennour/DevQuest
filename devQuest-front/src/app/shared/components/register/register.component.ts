import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordMatchValidator } from '../../validators/passwordMatchValidator';
import { RegistrationRequest } from '../../models/usermodels';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  step: number = 1;
  registerErrorMessage: string = '';

  registerForm: FormGroup = new FormGroup(
    {
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/), // At least one uppercase letter, one lowercase letter, and one number
      ]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    {
      validators: passwordMatchValidator('password', 'confirmPassword'),
    }
  );

  constructor(private authService: AuthService, private router: Router) {}

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  onSubmit($event: SubmitEvent) {
    $event.preventDefault();
  }

  togglePassword($event: MouseEvent) {
    $event.preventDefault();
    const passwordField = document.querySelector('#password');
    if (passwordField?.getAttribute('type') === 'password') {
      passwordField.setAttribute('type', 'text');
    } else {
      passwordField?.setAttribute('type', 'password');
    }
  }

  // Register Form Submission
  async submitRegisterForm() {
    let registrationData: RegistrationRequest = {
      username: this.registerForm.get('username')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
      role: 'USER',
    };

    try {
      const response = await firstValueFrom(
        this.authService.registerUser(registrationData)
      );
      console.log('Registration response: ', response);
      this.nextStep();
    } catch (error) {
      console.error('Registration error: ', error);
      this.registerErrorMessage = 'Registration failed. Please try again.';
    }
  }
}
