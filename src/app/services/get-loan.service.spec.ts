import { TestBed } from '@angular/core/testing';

import { GetLoanService } from './get-loan.service';

describe('GetLoanService', () => {
  let service: GetLoanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetLoanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
