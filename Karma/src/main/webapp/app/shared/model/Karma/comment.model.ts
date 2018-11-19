import { Moment } from 'moment';
import { IReply } from 'app/shared/model/Karma/reply.model';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

export interface IComment {
  id?: number;
  message?: string;
  date?: Moment;
  needId?: number;
  helpId?: number;
  postId?: number;
  replies?: IReply[];
  commentedUserId?: number;
  userChecks?: IUserCheck[];
}

export class Comment implements IComment {
  constructor(
    public id?: number,
    public message?: string,
    public date?: Moment,
    public needId?: number,
    public helpId?: number,
    public postId?: number,
    public replies?: IReply[],
    public commentedUserId?: number,
    public userChecks?: IUserCheck[]
  ) {}
}
