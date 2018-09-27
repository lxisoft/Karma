import { IPublisher } from 'app/shared/model//publisher.model';
import { IAuthor } from 'app/shared/model//author.model';

export interface IBook {
    id?: number;
    bookName?: string;
    price?: number;
    publisher?: IPublisher;
    authors?: IAuthor[];
}

export class Book implements IBook {
    constructor(
        public id?: number,
        public bookName?: string,
        public price?: number,
        public publisher?: IPublisher,
        public authors?: IAuthor[]
    ) {}
}
