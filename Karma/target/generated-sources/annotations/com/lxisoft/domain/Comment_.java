package com.lxisoft.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Instant> date;
	public static volatile SingularAttribute<Comment, Help> help;
	public static volatile SetAttribute<Comment, Reply> replies;
	public static volatile SingularAttribute<Comment, Need> need;
	public static volatile SingularAttribute<Comment, LoggedUser> commentedUser;
	public static volatile SingularAttribute<Comment, Violation> violation;
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, String> message;
	public static volatile SingularAttribute<Comment, NewsFeed> newsFeed;
	public static volatile SetAttribute<Comment, UserCheck> userChecks;

}

