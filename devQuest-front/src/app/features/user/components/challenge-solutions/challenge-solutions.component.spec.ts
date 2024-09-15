import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChallengeSolutionsComponent } from './challenge-solutions.component';

describe('ChallengeSolutionsComponent', () => {
  let component: ChallengeSolutionsComponent;
  let fixture: ComponentFixture<ChallengeSolutionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChallengeSolutionsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChallengeSolutionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
