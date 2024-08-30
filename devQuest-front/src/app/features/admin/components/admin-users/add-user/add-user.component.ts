import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css',
})
export class AddUserComponent implements OnInit {
  addUserForm!: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    this.addUserForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      fullName: new FormControl('', [Validators.required]),
      role: new FormControl(''),
      birthDate: new FormControl(Date.now(), [Validators.required]),
      gender: new FormControl(''),
      bio: new FormControl(''),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/),
      ]),
    });
  }

  onSubmit(event: Event) {
    event.preventDefault();
    console.log(this.addUserForm.value);
    // call the service to add the user
    this.usersService.createUser(this.addUserForm.value).subscribe(
      (res) => {
        console.log(res);
        this.addUserForm.reset();
        this.errorMessage = '';
        this.successMessage = 'User added successfully';
      },
      (error) => {
        console.error(error);
        this.successMessage = '';
        this.errorMessage = 'Failed to add user';
      }
    );
  }
}
