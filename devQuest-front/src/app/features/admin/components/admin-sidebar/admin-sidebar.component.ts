import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrl: './admin-sidebar.component.css',
})
export class AdminSidebarComponent {
  @Output() toggleSideNav = new EventEmitter<void>();
  @Input() isOpen = false;
  showUserLinks = false;
  showQuizzLinks = false;
  showCodingLinks = false;
  showTechLinks = false;
  showReportsLinks = false;

  onToggleSideNav(): void {
    this.isOpen = !this.isOpen;
    this.toggleSideNav.emit();
  }

  toggleUserLinks(): void {
    this.showUserLinks = !this.showUserLinks;
  }
  toggleQuizzLinks(): void {
    this.showQuizzLinks = !this.showQuizzLinks;
  }
  toggleCodingLinks(): void {
    this.showCodingLinks = !this.showCodingLinks;
  }
  toggleTechLinks(): void {
    this.showTechLinks = !this.showTechLinks;
  }
  toggleReportsLinks(): void {
    this.showReportsLinks = !this.showReportsLinks;
  }

  logout() {
    localStorage.removeItem('token');
    window.location.href = '/login';
  }
}
