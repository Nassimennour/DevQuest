// technology-list.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category, Technology } from '../../../models/admin-models';
import { TechnologyService } from '../../../services/technology.service';

@Component({
  selector: 'app-technology-list',
  templateUrl: './technology-list.component.html',
  styleUrls: ['./technology-list.component.css'],
})
export class TechnologyListComponent implements OnInit {
  technologies: Technology[] = [];
  editTechnologyForm!: FormGroup;
  selectedTechnology!: Technology;
  categories: Category[] = [];

  constructor(
    private technologyService: TechnologyService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadTechnologies();
    this.editTechnologyForm = this.fb.group({
      id: [{ value: '', disabled: true }],
      name: ['', Validators.required],
      overview: [''],
      logo: [''],
      categoryId: [''],
    });
  }

  loadTechnologies(): void {
    this.technologyService.getAllTechnologies().subscribe(
      (response) => {
        console.log('technologies:', response);
        this.technologies = response;
      },
      (error) => {
        console.error('Error fetching technologies:', error);
      }
    );
  }

  loadCategories(): void {
    console.log('loading categories');
    this.technologyService.getAllCategories().subscribe(
      (response) => {
        console.log('categories:', response);
        this.categories = response;
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  openEditModal(technology: Technology): void {
    if (this.categories.length === 0) {
      this.loadCategories();
    }
    this.selectedTechnology = technology;
    this.editTechnologyForm.patchValue({
      id: technology.id,
      name: technology.name,
      overview: technology.overview,
      logo: technology.logo,
      categoryId: technology.category.id,
    });
    const modal = document.getElementById('editModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  closeEditModal(): void {
    const modal = document.getElementById('editModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  onSubmit(): void {
    console.log('submitting form:', this.editTechnologyForm.value);
    if (this.editTechnologyForm.valid) {
      const updatedTechnology = {
        ...this.editTechnologyForm.value,
        id: this.selectedTechnology.id,
      };
      this.technologyService.updateTechnology(updatedTechnology).subscribe(
        (response) => {
          this.loadTechnologies();
          this.closeEditModal();
        },
        (error) => {
          console.error('Error updating technology:', error);
        }
      );
    }
  }

  confirmDelete(id: any): void {
    if (confirm('Are you sure you want to delete this technology?')) {
      this.technologyService.deleteTechnology(id as number).subscribe(
        (response) => {
          this.loadTechnologies();
        },
        (error) => {
          console.error('Error deleting technology:', error);
        }
      );
    }
  }
}
