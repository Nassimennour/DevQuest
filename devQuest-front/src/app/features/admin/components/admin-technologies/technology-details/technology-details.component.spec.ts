import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnologyDetailsComponent } from './technology-details.component';

describe('TechnologyDetailsComponent', () => {
  let component: TechnologyDetailsComponent;
  let fixture: ComponentFixture<TechnologyDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TechnologyDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechnologyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
