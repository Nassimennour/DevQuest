import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicNavComponent } from './basic-nav.component';

describe('BasicNavComponent', () => {
  let component: BasicNavComponent;
  let fixture: ComponentFixture<BasicNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicNavComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BasicNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
