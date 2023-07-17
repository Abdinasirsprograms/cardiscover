import { TestBed } from '@angular/core/testing';

import { carDiscoverHTTPService } from './car-discover-http.service';

describe('LocationDataService', () => {
  let service: carDiscoverHTTPService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(carDiscoverHTTPService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
