import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnologyChartComponent } from './technology-chart.component';

describe('TechnologyChartComponent', () => {
  let component: TechnologyChartComponent;
  let fixture: ComponentFixture<TechnologyChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TechnologyChartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechnologyChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
