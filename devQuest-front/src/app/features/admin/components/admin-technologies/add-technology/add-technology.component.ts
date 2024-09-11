// add-technology.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category, TechnologyDTO } from '../../../models/admin-models';
import { TechnologyService } from '../../../services/technology.service';

@Component({
  selector: 'app-add-technology',
  templateUrl: './add-technology.component.html',
  styleUrls: ['./add-technology.component.css'],
})
export class AddTechnologyComponent implements OnInit {
  addTechnologyForm!: FormGroup;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private technologyService: TechnologyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.addTechnologyForm = this.fb.group({
      name: ['', Validators.required],
      overview: ['', Validators.required],
      logo: ['', Validators.required],
      categoryId: ['', Validators.required],
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
    return this.addTechnologyForm.get('name');
  }

  get overview() {
    return this.addTechnologyForm.get('overview');
  }

  get logo() {
    return this.addTechnologyForm.get('logo');
  }

  get categoryId() {
    return this.addTechnologyForm.get('categoryId');
  }

  onSubmit(): void {
    if (this.addTechnologyForm.valid) {
      const newTechnology: TechnologyDTO = this.addTechnologyForm.value;
      this.technologyService.createCategory(newTechnology).subscribe(
        (response) => {
          this.router.navigate(['/admin/technology-list']);
        },
        (error) => {
          console.error('Error adding technology:', error);
        }
      );
    }
  }

  onReset(): void {
    this.addTechnologyForm.reset();
  }
}
