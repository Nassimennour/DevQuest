import { Component, OnInit } from '@angular/core';
import { Activity, AdminStats } from '../../models/admin-models';
import { StatsService } from '../../services/stats.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css',
})
export class AdminHomeComponent implements OnInit {
  currentDate = new Date();
  adminStats!: AdminStats;
  recentActivities: Activity[] = [];

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    this.statsService.getAdminStats().subscribe((stats) => {
      this.adminStats = stats;
    });
    this.statsService.getRecentActivities().subscribe(
      (activities) => {
        console.log('Recent activities : ', activities);
        this.recentActivities = activities.slice(0, 5);
      },
      (error) => {
        console.error('Error getting recent activities : ', error);
      }
    );
  }
}
