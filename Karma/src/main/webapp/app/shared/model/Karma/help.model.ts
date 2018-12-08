import { Moment } from 'moment';
import { IMedia } from 'app/shared/model/Karma/media.model';
import { IComment } from 'app/shared/model/Karma/comment.model';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

export interface IHelp {
  id?: number;
  time?: Moment;
  description?: string;
  proofs?: IMedia[];
  comments?: IComment[];
  approvalStatusId?: number;
  providedUserId?: number;
  fulfilledNeedId?: number;
  userChecks?: IUserCheck[];
}

export class Help implements IHelp {
  constructor(
    public id?: number,
    public time?: Moment,
    public description?: string,
    public proofs?: IMedia[],
    public comments?: IComment[],
    public approvalStatusId?: number,
    public providedUserId?: number,
    public fulfilledNeedId?: number,
    public userChecks?: IUserCheck[]
  ) {}
}
