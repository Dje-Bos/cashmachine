import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcuctDetailComponent } from './procuct-detail.component';

describe('ProcuctDetailComponent', () => {
  let component: ProcuctDetailComponent;
  let fixture: ComponentFixture<ProcuctDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcuctDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcuctDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
