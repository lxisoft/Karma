import { IBook } from 'app/shared/model//book.model';

export interface IAuthor {
    id?: number;
    authorName?: string;
    phoneNumber?: number;
    books?: IBook[];
}

export class Author implements IAuthor {
    constructor(public id?: number, public authorName?: string, public phoneNumber?: number, public books?: IBook[]) {}
}
