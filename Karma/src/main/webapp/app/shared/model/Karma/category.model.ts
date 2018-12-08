import { INeed } from 'app/shared/model/Karma/need.model';

export interface ICategory {
  id?: number;
  name?: string;
  subCategory?: string;
  needs?: INeed[];
}

export class Category implements ICategory {
  constructor(public id?: number, public name?: string, public subCategory?: string, public needs?: INeed[]) {}
}
