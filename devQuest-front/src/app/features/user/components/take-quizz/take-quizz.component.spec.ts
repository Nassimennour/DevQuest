import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeQuizzComponent } from './take-quizz.component';

describe('TakeQuizzComponent', () => {
  let component: TakeQuizzComponent;
  let fixture: ComponentFixture<TakeQuizzComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TakeQuizzComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TakeQuizzComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
