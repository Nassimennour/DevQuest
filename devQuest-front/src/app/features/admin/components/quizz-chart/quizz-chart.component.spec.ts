import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizzChartComponent } from './quizz-chart.component';

describe('QuizzChartComponent', () => {
  let component: QuizzChartComponent;
  let fixture: ComponentFixture<QuizzChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuizzChartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuizzChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
