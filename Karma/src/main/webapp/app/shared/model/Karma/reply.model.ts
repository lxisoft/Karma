import { Moment } from 'moment';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

export interface IReply {
  id?: number;
  message?: string;
  date?: Moment;
  commentId?: number;
  repliedUserId?: number;
  userChecks?: IUserCheck[];
}

export class Reply implements IReply {
  constructor(
    public id?: number,
    public message?: string,
    public date?: Moment,
    public commentId?: number,
    public repliedUserId?: number,
    public userChecks?: IUserCheck[]
  ) {}
}
