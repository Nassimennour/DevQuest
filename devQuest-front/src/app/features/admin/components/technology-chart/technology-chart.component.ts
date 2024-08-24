import { Component, OnInit } from '@angular/core';
import { StatsService } from '../../services/stats.service';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';

@Component({
  selector: 'app-technology-chart',
  templateUrl: './technology-chart.component.html',
  styleUrl: './technology-chart.component.css',
})
export class TechnologyChartComponent implements OnInit {
  public pieChartOptions: ChartOptions = {
    responsive: true,
  };

  public pieChartLabels: string[] = ['Angular', 'React', 'Vue'];
  public pieChartData: ChartDataset[] = [
    {
      data: [300, 500, 100],
      backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
      hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
      label: 'Technology Popularity',
    },
  ];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [
    {
      beforeInit: function (chart: any) {
        chart.legend.afterFit = function () {
          this.height = this.height + 20;
        };
      },
    },
  ];

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
    // Get the technology popularity stats from the stats service
  }
}
