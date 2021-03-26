import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestAppJHipsterTestModule } from '../../../test.module';
import { UserRolesComponent } from 'app/entities/user-roles/user-roles.component';
import { UserRolesService } from 'app/entities/user-roles/user-roles.service';
import { UserRoles } from 'app/shared/model/user-roles.model';

describe('Component Tests', () => {
  describe('UserRoles Management Component', () => {
    let comp: UserRolesComponent;
    let fixture: ComponentFixture<UserRolesComponent>;
    let service: UserRolesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestAppJHipsterTestModule],
        declarations: [UserRolesComponent],
      })
        .overrideTemplate(UserRolesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserRolesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserRolesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserRoles(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userRoles && comp.userRoles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
