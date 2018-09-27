export interface IPublisher {
    id?: number;
    publisherName?: string;
    address?: string;
}

export class Publisher implements IPublisher {
    constructor(public id?: number, public publisherName?: string, public address?: string) {}
}
