import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { INeed } from 'app/shared/model/Karma/need.model';

export interface IVerificationTeam {
  id?: number;
  approvalLevel?: string;
  approvingUsers?: IRegisteredUser[];
  needs?: INeed[];
}

export class VerificationTeam implements IVerificationTeam {
  constructor(public id?: number, public approvalLevel?: string, public approvingUsers?: IRegisteredUser[], public needs?: INeed[]) {}
}
