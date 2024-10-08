import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboardComponent {
  isSideNavOpen: boolean = false;

  onToggleSideNav(): void {
    this.isSideNavOpen = !this.isSideNavOpen;
  }
}
