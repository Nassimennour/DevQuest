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
  recentActivities: Activity[] = [
    {
      type: 'User registered',
      description: 'John Doe',
      optional: '',
      date: '2021-09-01T12:00:00Z',
    },
    {
      type: 'Quiz completed',
      description: 'Angular Quiz',
      optional: 'John Doe',
      date: '2021-09-01T12:00:00Z',
    },
    {
      type: 'Coding challenge completed',
      description: 'FizzBuzz',
      optional: 'John Doe',
      date: '2021-09-01T12:00:00Z',
    },
    {
      type: 'User registered',
      description: 'Jane Doe',
      optional: '',
      date: '2021-09-01T12:00:00Z',
    },
  ];

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    this.statsService.getAdminStats().subscribe((stats) => {
      this.adminStats = stats;
    });
    this.statsService.getRecentActivities().subscribe((activities) => {
      this.recentActivities = activities.slice(0, 5);
    });
  }
}
