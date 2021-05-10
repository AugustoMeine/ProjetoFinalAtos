import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BatePapoComponent } from './bate-papo.component';

describe('BatePapoComponent', () => {
  let component: BatePapoComponent;
  let fixture: ComponentFixture<BatePapoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BatePapoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BatePapoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
