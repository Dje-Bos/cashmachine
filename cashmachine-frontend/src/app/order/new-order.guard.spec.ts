import { TestBed, async, inject } from '@angular/core/testing';

import { NewOrderGuard } from './new-order.guard';

describe('NewOrderGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NewOrderGuard]
    });
  });

  it('should ...', inject([NewOrderGuard], (guard: NewOrderGuard) => {
    expect(guard).toBeTruthy();
  }));
});
