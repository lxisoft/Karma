package com.lxisoft.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reply.class)
public abstract class Reply_ {

	public static volatile SingularAttribute<Reply, Instant> date;
	public static volatile SingularAttribute<Reply, LoggedUser> repliedUser;
	public static volatile SingularAttribute<Reply, Comment> comment;
	public static volatile SingularAttribute<Reply, Long> id;
	public static volatile SingularAttribute<Reply, String> message;
	public static volatile SetAttribute<Reply, UserCheck> userChecks;

}

