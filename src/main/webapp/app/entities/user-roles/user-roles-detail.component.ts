import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserRoles } from 'app/shared/model/user-roles.model';

@Component({
  selector: 'jhi-user-roles-detail',
  templateUrl: './user-roles-detail.component.html',
})
export class UserRolesDetailComponent implements OnInit {
  userRoles: IUserRoles | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userRoles }) => (this.userRoles = userRoles));
  }

  previousState(): void {
    window.history.back();
  }
}
