package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Severity.class)
public abstract class Severity_ {

	public static volatile SetAttribute<Severity, Need> needs;
	public static volatile SingularAttribute<Severity, String> severityLevel;
	public static volatile SingularAttribute<Severity, Long> id;

}

