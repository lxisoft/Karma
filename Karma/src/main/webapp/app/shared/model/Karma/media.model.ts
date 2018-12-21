export interface IMedia {
  id?: number;
  fileName?: string;
  url?: string;
  extension?: string;
  fileContentType?: string;
  file?: any;
  needId?: number;
  helpId?: number;
  postId?: number;
}

export class Media implements IMedia {
  constructor(
    public id?: number,
    public fileName?: string,
    public url?: string,
    public extension?: string,
    public fileContentType?: string,
    public file?: any,
    public needId?: number,
    public helpId?: number,
    public postId?: number
  ) {}
}
