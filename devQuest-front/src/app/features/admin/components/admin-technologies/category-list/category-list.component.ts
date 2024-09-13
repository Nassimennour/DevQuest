// category-list.component.ts
import { Component, OnInit } from '@angular/core';
import { Category } from '../../../models/admin-models';
import { TechnologyService } from '../../../services/technology.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css'],
})
export class CategoryListComponent implements OnInit {
  categories: Category[] = [];

  constructor(private technologyService: TechnologyService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.technologyService.getAllCategories().subscribe(
      (response) => {
        console.log('response:', response);
        this.categories = response;
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }
}
