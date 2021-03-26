import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserRoles } from 'app/shared/model/user-roles.model';
import { UserRolesService } from './user-roles.service';
import { UserRolesDeleteDialogComponent } from './user-roles-delete-dialog.component';

@Component({
  selector: 'jhi-user-roles',
  templateUrl: './user-roles.component.html',
})
export class UserRolesComponent implements OnInit, OnDestroy {
  userRoles?: IUserRoles[];
  eventSubscriber?: Subscription;

  constructor(protected userRolesService: UserRolesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.userRolesService.query().subscribe((res: HttpResponse<IUserRoles[]>) => (this.userRoles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserRoles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserRoles): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserRoles(): void {
    this.eventSubscriber = this.eventManager.subscribe('userRolesListModification', () => this.loadAll());
  }

  delete(userRoles: IUserRoles): void {
    const modalRef = this.modalService.open(UserRolesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userRoles = userRoles;
  }
}
