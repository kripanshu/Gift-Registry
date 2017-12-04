import { TestBed, inject } from '@angular/core/testing';

import { SharedregistryService } from './sharedregistry.service';

describe('SharedregistryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SharedregistryService]
    });
  });

  it('should be created', inject([SharedregistryService], (service: SharedregistryService) => {
    expect(service).toBeTruthy();
  }));
});
