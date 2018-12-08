import { Moment } from 'moment';

export interface IFeed {
  id?: number;
  title?: string;
  type?: string;
  date?: Moment;
  referenceId?: number;
  registeredUserId?: number;
}

export class Feed implements IFeed {
  constructor(
    public id?: number,
    public title?: string,
    public type?: string,
    public date?: Moment,
    public referenceId?: number,
    public registeredUserId?: number
  ) {}
}
