import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestuarantsComponent } from './restuarants.component';

describe('RestuarantsComponent', () => {
  let component: RestuarantsComponent;
  let fixture: ComponentFixture<RestuarantsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestuarantsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestuarantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
