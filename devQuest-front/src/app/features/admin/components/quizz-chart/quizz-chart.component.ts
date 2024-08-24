import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { StatsService } from '../../services/stats.service';
import { QuizzCompletionStats } from '../../models/admin-models';

@Component({
  selector: 'app-quizz-chart',
  templateUrl: './quizz-chart.component.html',
  styleUrl: './quizz-chart.component.css',
})
export class QuizzChartComponent implements OnInit {
  public lineChartOptions: ChartOptions = {
    responsive: true,
  };
  public lineChartLabels: string[] = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
  ];
  public lineChartType: ChartType = 'line';
  public lineChartLegend = true;
  public lineChartPlugins = [];
  public lineChartData: ChartDataset[] = [
    {
      data: [65, 59, 80, 81, 56, 55, 40],
      label: 'Quizzes Completed',
      borderColor: '#3cba9f',
      tension: 0.1,
      fill: false,
    },
  ];

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    // this.statsService.getQuizzCompletionStats().subscribe(
    //   (stats) => {
    //     this.lineChartData[0].data = stats.map(
    //       (stat: QuizzCompletionStats) => stat.count
    //     );
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
  }
}
