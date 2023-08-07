import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustHomeComponent } from './cust-home.component';

describe('CustHomeComponent', () => {
  let component: CustHomeComponent;
  let fixture: ComponentFixture<CustHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustHomeComponent]
    });
    fixture = TestBed.createComponent(CustHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
