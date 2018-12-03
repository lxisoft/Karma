export interface IIdentityProofType {
  id?: number;
  type?: string;
}

export class IdentityProofType implements IIdentityProofType {
  constructor(public id?: number, public type?: string) {}
}
