export interface IIdentityProof {
  id?: number;
  idNo?: string;
  identityProofTypeId?: number;
  ownerId?: number;
}

export class IdentityProof implements IIdentityProof {
  constructor(public id?: number, public idNo?: string, public identityProofTypeId?: number, public ownerId?: number) {}
}
