import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserRoles, UserRoles } from 'app/shared/model/user-roles.model';
import { UserRolesService } from './user-roles.service';
import { UserRolesComponent } from './user-roles.component';
import { UserRolesDetailComponent } from './user-roles-detail.component';
import { UserRolesUpdateComponent } from './user-roles-update.component';

@Injectable({ providedIn: 'root' })
export class UserRolesResolve implements Resolve<IUserRoles> {
  constructor(private service: UserRolesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserRoles> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userRoles: HttpResponse<UserRoles>) => {
          if (userRoles.body) {
            return of(userRoles.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserRoles());
  }
}

export const userRolesRoute: Routes = [
  {
    path: '',
    component: UserRolesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testAppJHipsterApp.userRoles.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserRolesDetailComponent,
    resolve: {
      userRoles: UserRolesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testAppJHipsterApp.userRoles.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserRolesUpdateComponent,
    resolve: {
      userRoles: UserRolesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testAppJHipsterApp.userRoles.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserRolesUpdateComponent,
    resolve: {
      userRoles: UserRolesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testAppJHipsterApp.userRoles.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
