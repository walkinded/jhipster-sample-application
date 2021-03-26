import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserRoles } from 'app/shared/model/user-roles.model';
import { UserRolesService } from './user-roles.service';

@Component({
  templateUrl: './user-roles-delete-dialog.component.html',
})
export class UserRolesDeleteDialogComponent {
  userRoles?: IUserRoles;

  constructor(protected userRolesService: UserRolesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userRolesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userRolesListModification');
      this.activeModal.close();
    });
  }
}
