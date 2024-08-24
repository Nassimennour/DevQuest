import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { StatsService } from '../../services/stats.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-user-activity-chart',
  templateUrl: './user-activity-chart.component.html',
  styleUrl: './user-activity-chart.component.css',
})
export class UserActivityChartComponent implements OnInit {
  public lineChartOptions: ChartOptions = {
    responsive: true,
  };
  public lineChartLabels: string[] = Array.from({ length: 30 }, (_, i) =>
    (i + 1).toString()
  );
  public lineChartType: ChartType = 'line';
  public lineChartLegend = true;
  public lineChartPlugins = [];
  public lineChartData: ChartDataset[] = [
    {
      data: Array.from({ length: 30 }, () => Math.floor(Math.random() * 10)),
      label: 'User Activity',
      borderColor: '#3cba9f',
      tension: 0.1,
      fill: false,
    },
  ];
  MonthYear: string = new Date().toLocaleString('default', {
    month: 'long',
    year: 'numeric',
  });

  constructor(private statsService: StatsService, private datePipe: DatePipe) {}

  ngOnInit(): void {
    // this.statsService.getUserActivity().subscribe(
    //   (activities) => {
    //     this.lineChartData[0].data = activities.map(
    //       (activity) => activity.activityCount
    //     );
    //     this.lineChartLabels = activities.map(
    //       (activity) => this.datePipe.transform(activity.date, 'd')!
    //     );
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
  }
}
