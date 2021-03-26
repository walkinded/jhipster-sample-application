import { Moment } from 'moment';
import { Priority } from 'app/shared/model/enumerations/priority.model';

export interface ITask {
  id?: number;
  title?: string;
  description?: string;
  startDate?: Moment;
  endDate?: Moment;
  priority?: Priority;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public priority?: Priority
  ) {}
}
