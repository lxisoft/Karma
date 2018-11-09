package com.lxisoft.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NewsFeed.class)
public abstract class NewsFeed_ {

	public static volatile SingularAttribute<NewsFeed, Instant> date;
	public static volatile SetAttribute<NewsFeed, Media> attachments;
	public static volatile SetAttribute<NewsFeed, Comment> comments;
	public static volatile SingularAttribute<NewsFeed, LoggedUser> loggedUser;
	public static volatile SingularAttribute<NewsFeed, String> description;
	public static volatile SingularAttribute<NewsFeed, Long> id;
	public static volatile SetAttribute<NewsFeed, UserCheck> userChecks;

}

