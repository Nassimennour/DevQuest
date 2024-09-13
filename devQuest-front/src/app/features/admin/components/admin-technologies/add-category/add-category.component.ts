// add-category.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category, CategoryDTO } from '../../../models/admin-models';
import { TechnologyService } from '../../../services/technology.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css'],
})
export class AddCategoryComponent implements OnInit {
  addCategoryForm!: FormGroup;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private technologyService: TechnologyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.addCategoryForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      parentCategoryId: [''],
    });

    this.loadCategories();
  }

  loadCategories(): void {
    this.technologyService.getAllCategories().subscribe(
      (response) => {
        this.categories = response;
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  get name() {
    return this.addCategoryForm.get('name');
  }

  get description() {
    return this.addCategoryForm.get('description');
  }

  get parentCategoryId() {
    return this.addCategoryForm.get('parentCategoryId');
  }

  onSubmit(): void {
    if (this.addCategoryForm.valid) {
      const newCategory: CategoryDTO = this.addCategoryForm.value;
      this.technologyService.createCategory(newCategory).subscribe(
        (response) => {
          this.router.navigate(['/admin/categories']);
        },
        (error) => {
          console.error('Error adding category:', error);
        }
      );
    }
  }

  onReset(): void {
    this.addCategoryForm.reset();
  }
}
