import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestAppJHipsterTestModule } from '../../../test.module';
import { UserRolesDetailComponent } from 'app/entities/user-roles/user-roles-detail.component';
import { UserRoles } from 'app/shared/model/user-roles.model';

describe('Component Tests', () => {
  describe('UserRoles Management Detail Component', () => {
    let comp: UserRolesDetailComponent;
    let fixture: ComponentFixture<UserRolesDetailComponent>;
    const route = ({ data: of({ userRoles: new UserRoles(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestAppJHipsterTestModule],
        declarations: [UserRolesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserRolesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserRolesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userRoles on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userRoles).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
