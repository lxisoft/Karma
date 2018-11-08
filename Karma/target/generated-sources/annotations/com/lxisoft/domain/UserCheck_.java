package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserCheck.class)
public abstract class UserCheck_ {

	public static volatile SingularAttribute<UserCheck, String> voteType;
	public static volatile SingularAttribute<UserCheck, Violation> violation;
	public static volatile SingularAttribute<UserCheck, Need> checkedNeed;
	public static volatile SingularAttribute<UserCheck, Comment> comment;
	public static volatile SingularAttribute<UserCheck, Help> checkedHelp;
	public static volatile SingularAttribute<UserCheck, Long> id;
	public static volatile SingularAttribute<UserCheck, LoggedUser> checkedUser;
	public static volatile SingularAttribute<UserCheck, String> category;
	public static volatile SingularAttribute<UserCheck, Reply> reply;
	public static volatile SingularAttribute<UserCheck, NewsFeed> newsFeed;

}

