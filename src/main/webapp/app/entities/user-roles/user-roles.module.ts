import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestAppJHipsterSharedModule } from 'app/shared/shared.module';
import { UserRolesComponent } from './user-roles.component';
import { UserRolesDetailComponent } from './user-roles-detail.component';
import { UserRolesUpdateComponent } from './user-roles-update.component';
import { UserRolesDeleteDialogComponent } from './user-roles-delete-dialog.component';
import { userRolesRoute } from './user-roles.route';

@NgModule({
  imports: [TestAppJHipsterSharedModule, RouterModule.forChild(userRolesRoute)],
  declarations: [UserRolesComponent, UserRolesDetailComponent, UserRolesUpdateComponent, UserRolesDeleteDialogComponent],
  entryComponents: [UserRolesDeleteDialogComponent],
})
export class TestAppJHipsterUserRolesModule {}
