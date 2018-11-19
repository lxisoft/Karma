import { INeed } from 'app/shared/model/Karma/need.model';

export interface ISeverity {
  id?: number;
  severityLevel?: string;
  needs?: INeed[];
}

export class Severity implements ISeverity {
  constructor(public id?: number, public severityLevel?: string, public needs?: INeed[]) {}
}
