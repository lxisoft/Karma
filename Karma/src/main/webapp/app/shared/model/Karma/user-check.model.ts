export interface IUserCheck {
  id?: number;
  voteType?: string;
  category?: string;
  checkedNeedId?: number;
  checkedUserId?: number;
  commentId?: number;
  replyId?: number;
  postId?: number;
  checkedHelpId?: number;
}

export class UserCheck implements IUserCheck {
  constructor(
    public id?: number,
    public voteType?: string,
    public category?: string,
    public checkedNeedId?: number,
    public checkedUserId?: number,
    public commentId?: number,
    public replyId?: number,
    public postId?: number,
    public checkedHelpId?: number
  ) {}
}
