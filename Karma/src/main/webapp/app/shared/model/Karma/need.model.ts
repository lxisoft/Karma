import { Moment } from 'moment';
import { IMedia } from 'app/shared/model/Karma/media.model';
import { IHelp } from 'app/shared/model/Karma/help.model';
import { IComment } from 'app/shared/model/Karma/comment.model';
import { ICategory } from 'app/shared/model/Karma/category.model';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

export interface INeed {
  id?: number;
  description?: string;
  beneficiaryType?: string;
  date?: Moment;
  proofs?: IMedia[];
  helps?: IHelp[];
  comments?: IComment[];
  severityId?: number;
  verificationTeamId?: number;
  approvalStatusId?: number;
  personInChargeId?: number;
  categories?: ICategory[];
  postedUserId?: number;
  userChecks?: IUserCheck[];
}

export class Need implements INeed {
  constructor(
    public id?: number,
    public description?: string,
    public beneficiaryType?: string,
    public date?: Moment,
    public proofs?: IMedia[],
    public helps?: IHelp[],
    public comments?: IComment[],
    public severityId?: number,
    public verificationTeamId?: number,
    public approvalStatusId?: number,
    public personInChargeId?: number,
    public categories?: ICategory[],
    public postedUserId?: number,
    public userChecks?: IUserCheck[]
  ) {}
}
