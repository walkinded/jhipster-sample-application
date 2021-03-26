import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserRoles, UserRoles } from 'app/shared/model/user-roles.model';
import { UserRolesService } from './user-roles.service';

@Component({
  selector: 'jhi-user-roles-update',
  templateUrl: './user-roles-update.component.html',
})
export class UserRolesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idUser: [],
    idRole: [],
  });

  constructor(protected userRolesService: UserRolesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userRoles }) => {
      this.updateForm(userRoles);
    });
  }

  updateForm(userRoles: IUserRoles): void {
    this.editForm.patchValue({
      id: userRoles.id,
      idUser: userRoles.idUser,
      idRole: userRoles.idRole,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userRoles = this.createFromForm();
    if (userRoles.id !== undefined) {
      this.subscribeToSaveResponse(this.userRolesService.update(userRoles));
    } else {
      this.subscribeToSaveResponse(this.userRolesService.create(userRoles));
    }
  }

  private createFromForm(): IUserRoles {
    return {
      ...new UserRoles(),
      id: this.editForm.get(['id'])!.value,
      idUser: this.editForm.get(['idUser'])!.value,
      idRole: this.editForm.get(['idRole'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserRoles>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
