package com.lxisoft.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Violation.class)
public abstract class Violation_ {

	public static volatile SingularAttribute<Violation, Instant> date;
	public static volatile SingularAttribute<Violation, Boolean> isAnonymous;
	public static volatile SetAttribute<Violation, Comment> comments;
	public static volatile SetAttribute<Violation, Media> proofs;
	public static volatile SingularAttribute<Violation, String> description;
	public static volatile SingularAttribute<Violation, Long> id;
	public static volatile SetAttribute<Violation, UserCheck> userChecks;

}

