import { TestBed, inject } from '@angular/core/testing';

import { RegisteryService } from './registery.service';

describe('RegisteryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RegisteryService]
    });
  });

  it('should be created', inject([RegisteryService], (service: RegisteryService) => {
    expect(service).toBeTruthy();
  }));
});
