import { TestBed, async, inject } from '@angular/core/testing';

import { UserResolveGuard } from './user-resolve.guard';

describe('UserResolveGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserResolveGuard]
    });
  });

  it('should ...', inject([UserResolveGuard], (guard: UserResolveGuard) => {
    expect(guard).toBeTruthy();
  }));
});
