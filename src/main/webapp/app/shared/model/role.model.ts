import { Roles } from 'app/shared/model/enumerations/roles.model';

export interface IRole {
  id?: number;
  name?: Roles;
}

export class Role implements IRole {
  constructor(public id?: number, public name?: Roles) {}
}
