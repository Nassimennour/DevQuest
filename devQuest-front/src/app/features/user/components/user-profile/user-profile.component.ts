// user-profile.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  Technology,
  UserProfile,
  ChangePasswordDTO,
} from '../../../../shared/models/usermodels';
import { UserService } from '../../services/user.service';

declare var bootstrap: any;

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  userProfile: UserProfile | null = null;
  technologies: Technology[] = [];
  editProfileForm: FormGroup;
  addSkillForm: FormGroup;
  changePasswordForm: FormGroup;

  constructor(private userService: UserService, private fb: FormBuilder) {
    this.editProfileForm = this.fb.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      profilePicture: ['', Validators.required],
      birthDate: ['', Validators.required],
      gender: ['', Validators.required],
    });

    this.addSkillForm = this.fb.group({
      technology: ['', Validators.required],
    });

    this.changePasswordForm = this.fb.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.fetchUserProfile();
    this.fetchTechnologies();
  }

  fetchUserProfile(): void {
    this.userService.getMyProfile().subscribe(
      (profile) => {
        console.log('User profile:', profile);
        this.userProfile = profile;
        this.editProfileForm.patchValue(profile);
      },
      (error) => {
        console.error('Error fetching user profile:', error);
      }
    );
  }

  fetchTechnologies(): void {
    this.userService.getAllTechnologies().subscribe(
      (technologies: Technology[]) => {
        console.log('Technologies:', technologies);
        this.technologies = technologies;
      },
      (error) => {
        console.error('Error fetching technologies:', error);
      }
    );
  }

  openEditModal(): void {
    const modalElement = document.getElementById('editProfileModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  openAddSkillModal(): void {
    const modalElement = document.getElementById('addSkillModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  openChangePasswordModal(): void {
    const modalElement = document.getElementById('changePasswordModal');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
  }

  updateProfile(): void {
    if (this.editProfileForm.valid) {
      console.log('Updating profile:', this.editProfileForm.value);
      const updatedProfile = {
        ...this.userProfile,
        ...this.editProfileForm.value,
        skills: [],
      };
      console.log('Updated profile:', updatedProfile);
      this.userService.updateMyProfile(updatedProfile).subscribe(
        (response) => {
          console.log('Profile updated:', response);
          this.userProfile = response;
          const modalElement = document.getElementById('editProfileModal');
          const modal = bootstrap.Modal.getInstance(modalElement);
          modal.hide();
        },
        (error) => {
          console.error('Error updating profile:', error);
        }
      );
    }
  }

  addSkill(): void {
    if (this.addSkillForm.valid) {
      const technologyId = this.addSkillForm.value.technology;
      this.userService.addSkill(technologyId).subscribe(
        (response) => {
          this.fetchUserProfile();
          const modalElement = document.getElementById('addSkillModal');
          const modal = bootstrap.Modal.getInstance(modalElement);
          modal.hide();
        },
        (error) => {
          console.error('Error adding skill:', error);
        }
      );
    }
  }

  changePassword(): void {
    if (this.changePasswordForm.valid) {
      const changePasswordDTO: ChangePasswordDTO = {
        ...this.changePasswordForm.value,
        username: this.userProfile?.username,
      };
      this.userService.changePassword(changePasswordDTO).subscribe(
        (response) => {
          console.log('Password changed successfully:', response);
          alert('Password changed successfully');
          const modalElement = document.getElementById('changePasswordModal');
          const modal = bootstrap.Modal.getInstance(modalElement);
          modal.hide();
        },
        (error) => {
          console.error('Error changing password:', error);
          alert('Error changing password: ' + error.error);
        }
      );
    }
  }
}
