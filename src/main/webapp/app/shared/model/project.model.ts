export interface IProject {
  id?: number;
  title?: string;
  description?: string;
  tasks?: number;
  users?: number;
}

export class Project implements IProject {
  constructor(public id?: number, public title?: string, public description?: string, public tasks?: number, public users?: number) {}
}
