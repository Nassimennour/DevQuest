import {
  Component,
  Input,
  OnInit,
  OnChanges,
  SimpleChanges,
  Output,
  EventEmitter,
} from '@angular/core';
import { User } from '../../../models/admin-models';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-edit-user-modal',
  templateUrl: './edit-user-modal.component.html',
  styleUrls: ['./edit-user-modal.component.css'],
})
export class EditUserModalComponent implements OnInit, OnChanges {
  @Input() user!: User;
  @Output() userUpdated = new EventEmitter<void>();
  editUserForm!: FormGroup;
  selectedFile: File | null = null;

  constructor(private fb: FormBuilder, private usersService: UsersService) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['user'] && !changes['user'].isFirstChange()) {
      this.updateForm();
    }
  }

  private initializeForm(): void {
    this.editUserForm = this.fb.group({
      username: [''],
      email: [''],
      fullName: [''],
      role: [''],
      gender: [''],
      bio: [''],
      birthDate: [''],
      profilePicture: [''],
      isDarkMode: [false],
    });
  }

  private updateForm(): void {
    if (this.user && this.editUserForm) {
      this.editUserForm.patchValue({
        username: this.user.username,
        email: this.user.email,
        fullName: this.user.fullName,
        role: this.user.role,
        gender: this.user.gender,
        bio: this.user.bio,
        birthDate: this.user.birthDate,
        isDarkMode: this.user.isDarkMode,
      });
    }
  }

  openModal(user: User): void {
    this.user = user;
    this.updateForm();
    const modal = document.getElementById('editUserModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  closeModal(): void {
    const modal = document.getElementById('editUserModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onSubmit(): void {
    if (this.editUserForm.valid) {
      console.log('Submitting form...');
      const updatedUser = { ...this.editUserForm.value, id: this.user.id };
      console.log('Updated user:', updatedUser);
      this.usersService.updateUser(updatedUser).subscribe(
        (user) => {
          console.log('User updated successfully:', user);
          this.userUpdated.emit();
          this.closeModal();
        },
        (error) => {
          console.log('Error updating user:', error);
        }
      );
    }
  }
}
