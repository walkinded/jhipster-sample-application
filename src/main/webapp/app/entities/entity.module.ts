import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'project',
        loadChildren: () => import('./project/project.module').then(m => m.TestAppJHipsterProjectModule),
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.TestAppJHipsterTaskModule),
      },
      {
        path: 'role',
        loadChildren: () => import('./role/role.module').then(m => m.TestAppJHipsterRoleModule),
      },
      {
        path: 'user-roles',
        loadChildren: () => import('./user-roles/user-roles.module').then(m => m.TestAppJHipsterUserRolesModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TestAppJHipsterEntityModule {}
