export interface IUserRoles {
  id?: number;
  idUser?: number;
  idRole?: number;
}

export class UserRoles implements IUserRoles {
  constructor(public id?: number, public idUser?: number, public idRole?: number) {}
}
