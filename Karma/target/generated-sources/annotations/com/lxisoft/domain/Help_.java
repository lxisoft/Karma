package com.lxisoft.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Help.class)
public abstract class Help_ {

	public static volatile SingularAttribute<Help, ApprovalStatus> approvalStatus;
	public static volatile SingularAttribute<Help, Need> fulfilledNeed;
	public static volatile SetAttribute<Help, Comment> comments;
	public static volatile SetAttribute<Help, Media> proofs;
	public static volatile SingularAttribute<Help, String> description;
	public static volatile SingularAttribute<Help, Long> id;
	public static volatile SingularAttribute<Help, Instant> time;
	public static volatile SingularAttribute<Help, LoggedUser> providedUser;
	public static volatile SetAttribute<Help, UserCheck> userChecks;

}

