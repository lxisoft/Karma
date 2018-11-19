import { Moment } from 'moment';
import { IMedia } from 'app/shared/model/Karma/media.model';
import { IComment } from 'app/shared/model/Karma/comment.model';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

export interface IPost {
  id?: number;
  description?: string;
  date?: Moment;
  attachments?: IMedia[];
  comments?: IComment[];
  registeredUserId?: number;
  userChecks?: IUserCheck[];
}

export class Post implements IPost {
  constructor(
    public id?: number,
    public description?: string,
    public date?: Moment,
    public attachments?: IMedia[],
    public comments?: IComment[],
    public registeredUserId?: number,
    public userChecks?: IUserCheck[]
  ) {}
}
