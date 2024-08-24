import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/admin-models';
import { UsersService } from '../../../services/users.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.css',
  providers: [DatePipe],
})
export class UsersListComponent implements OnInit {
  headers: string[] = [
    'ID',
    'USERNAME',
    'EMAIL',
    'FULL NAME',
    'ROLE',
    'GENDER',
    'STATUS',
    'CREATED AT',
    'ACTIONS',
  ];

  users: User[] = [
    {
      id: 1,
      username: 'john_doe',
      email: 'john@gmail.com',
      fullName: 'John Doe',
      role: 'ADMIN',
      gender: 'MALE',
      bio: 'I am a software engineer',
      birthDate: '1990-01-01',
      profilePicture: 'https://www.google.com',
      isVerified: true,
      registrationDate: '2021-01-01',
      isDarkMode: false,
    },
    {
      id: 2,
      username: 'jane_doe',
      email: 'jane@gmail.com',
      fullName: 'Jane Doe',
      gender: 'FEMALE',
      role: 'USER',
      bio: 'I am a software engineer',
      birthDate: '1990-01-01',
      profilePicture: 'https://www.google.com',
      isVerified: false,
      registrationDate: '2021-01-01',
      isDarkMode: true,
    },
    {
      id: 3,
      username: 'joe_doe',
      email: 'joe@gmail.com',
      fullName: 'Joe Doe',
      gender: 'MALE',
      role: 'USER',
      bio: 'I am a software engineer',
      birthDate: '1990-01-01',
      profilePicture: 'https://www.google.com',
      isVerified: true,
      registrationDate: '2021-01-01',
      isDarkMode: false,
    },
  ];

  // pagination logic
  paginatedUsers: User[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalPages: number = 0;
  pages: number[] = [];
  // filtering logic
  filteredUsers: User[] = [];
  searchQuery: string = '';
  selectedFilter: string = 'all';
  userIdToDelete: number | null = null;

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    // this.getUsers();
    this.filteredUsers = this.users;
    this.updatePagination();
  }

  getUsers() {
    this.usersService.getAllUsers().subscribe(
      (users) => {
        this.users = users;
      },
      (error) => {
        console.log('Error getting users list : ', error);
      }
    );
  }

  onSearch(event: any) {
    this.searchQuery = event.target.value;
    this.filterUsers();
  }

  onFilter(event: any) {
    this.selectedFilter = event.target.value;
    this.filterUsers();
  }

  filterUsers() {
    this.filteredUsers = this.users.filter((user) => {
      const matchesSearch = Object.values(user).some((value) =>
        value.toString().toLowerCase().includes(this.searchQuery.toLowerCase())
      );
      const matchesFilter =
        this.selectedFilter === 'all' || user.role === this.selectedFilter;
      return matchesSearch && matchesFilter;
    });
    this.updatePagination();
  }

  updatePagination() {
    this.totalPages = Math.ceil(this.filteredUsers.length / this.itemsPerPage);
    this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
    this.setPage(1);
  }

  setPage(page: number) {
    if (page < 1 || page > this.totalPages) {
      return;
    }
    this.currentPage = page;
    const startIndex = (page - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedUsers = this.filteredUsers.slice(startIndex, endIndex);
  }

  confirmDelete(id: number) {
    const confirmed = confirm('Are you sure you want to delete this user?');
    if (confirmed) {
      this.usersService.deleteUser(id).subscribe(
        (response) => {
          // this.getUsers();
        },
        (error) => {
          console.log('Error deleting user : ', error);
        }
      );
    }
  }
}
