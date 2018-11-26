import { Moment } from 'moment';
import { IAddress } from 'app/shared/model/Karma/address.model';
import { INeed } from 'app/shared/model/Karma/need.model';
import { IHelp } from 'app/shared/model/Karma/help.model';
import { IPost } from 'app/shared/model/Karma/post.model';
import { IFeed } from 'app/shared/model/Karma/feed.model';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';
import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';

export interface IRegisteredUser {
  id?: number;
  email?: string;
  firstName?: string;
  lastName?: string;
  rating?: number;
  description?: string;
  profession?: string;
  gender?: string;
  dob?: Moment;
  bloodGroup?: string;
  emotionalQuotient?: number;
  socialQuotient?: number;
  happinessIndex?: number;
  profilePicId?: number;
  addresses?: IAddress[];
  needs?: INeed[];
  helps?: IHelp[];
  posts?: IPost[];
  feeds?: IFeed[];
  followers?: IRegisteredUser[];
  checkedNeeds?: IUserCheck[];
  verificationTeams?: IVerificationTeam[];
  followingUsers?: IRegisteredUser[];
}

export class RegisteredUser implements IRegisteredUser {
  constructor(
    public id?: number,
    public email?: string,
    public firstName?: string,
    public lastName?: string,
    public rating?: number,
    public description?: string,
    public profession?: string,
    public gender?: string,
    public dob?: Moment,
    public bloodGroup?: string,
    public emotionalQuotient?: number,
    public socialQuotient?: number,
    public happinessIndex?: number,
    public profilePicId?: number,
    public addresses?: IAddress[],
    public needs?: INeed[],
    public helps?: IHelp[],
    public posts?: IPost[],
    public feeds?: IFeed[],
    public followers?: IRegisteredUser[],
    public checkedNeeds?: IUserCheck[],
    public verificationTeams?: IVerificationTeam[],
    public followingUsers?: IRegisteredUser[]
  ) {}
}
