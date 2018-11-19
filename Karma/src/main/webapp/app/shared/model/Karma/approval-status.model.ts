import { INeed } from 'app/shared/model/Karma/need.model';
import { IHelp } from 'app/shared/model/Karma/help.model';

export interface IApprovalStatus {
  id?: number;
  status?: string;
  needs?: INeed[];
  helps?: IHelp[];
}

export class ApprovalStatus implements IApprovalStatus {
  constructor(public id?: number, public status?: string, public needs?: INeed[], public helps?: IHelp[]) {}
}
