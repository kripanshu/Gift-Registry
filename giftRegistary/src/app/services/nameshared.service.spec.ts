import { TestBed, inject } from '@angular/core/testing';

import { NamesharedService } from './nameshared.service';

describe('NamesharedService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NamesharedService]
    });
  });

  it('should be created', inject([NamesharedService], (service: NamesharedService) => {
    expect(service).toBeTruthy();
  }));
});
