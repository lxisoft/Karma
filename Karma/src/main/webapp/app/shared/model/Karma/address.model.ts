export interface IAddress {
  id?: number;
  houseName?: string;
  place?: string;
  city?: string;
  state?: string;
  country?: string;
  zip?: number;
  registeredUserId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public houseName?: string,
    public place?: string,
    public city?: string,
    public state?: string,
    public country?: string,
    public zip?: number,
    public registeredUserId?: number
  ) {}
}
