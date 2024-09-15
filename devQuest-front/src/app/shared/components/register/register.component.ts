import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordMatchValidator } from '../../validators/passwordMatchValidator';
import { RegistrationRequest, UserProfile } from '../../models/usermodels';
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
  registeredUser: UserProfile | null = null;

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
        // at least one number, one lowercase and one uppercase letter and one special character
        Validators.pattern(
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
        ),
      ]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    {
      validators: passwordMatchValidator('password', 'confirmPassword'),
    }
  );

  detailsForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    profilePicture: new FormControl(''),
    birthDate: new FormControl('', [Validators.required]),
    bio: new FormControl(''),
    gender: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private router: Router) {}

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  onSubmit($event: SubmitEvent) {
    $event.preventDefault();
    if (this.detailsForm?.valid) {
      this.submitDetailsForm();
    } else {
      console.error('Form is invalid');
    }
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
      username: this.registerForm?.get('username')?.value,
      email: this.registerForm?.get('email')?.value,
      password: this.registerForm?.get('password')?.value,
      role: 'USER',
    };

    try {
      const response = await firstValueFrom(
        this.authService.registerUser(registrationData)
      );
      console.log('Registration response: ', response);
      this.registeredUser = response.body;
      this.nextStep();
    } catch (error) {
      console.error('Registration error: ', error);
      this.registerErrorMessage = 'Registration failed. Please try again.';
    }
  }

  // Details Form Submission

  submitDetailsForm() {
    console.log('Submitting details form');
    let detailsData = {
      ...this.registeredUser,
      ...this.detailsForm.value,
      fullname: `${this.detailsForm.value.firstName} ${this.detailsForm.value.lastName}`,
      registrationDate: new Date().toISOString(),
    };
    console.log('Details data: ', detailsData);
    this.authService.createUserProfile(detailsData).subscribe(
      (response) => {
        console.log('User profile created: ', response);
        this.nextStep();
      },
      (error) => {
        console.error('Error creating user profile: ', error);
      }
    );
  }
}
