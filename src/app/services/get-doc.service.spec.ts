import { TestBed } from '@angular/core/testing';

import { GetDocService } from './get-doc.service';

describe('GetDocService', () => {
  let service: GetDocService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetDocService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
