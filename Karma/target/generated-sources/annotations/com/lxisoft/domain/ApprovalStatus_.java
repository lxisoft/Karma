package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ApprovalStatus.class)
public abstract class ApprovalStatus_ {

	public static volatile SetAttribute<ApprovalStatus, Need> needs;
	public static volatile SingularAttribute<ApprovalStatus, Long> id;
	public static volatile SetAttribute<ApprovalStatus, Help> helps;
	public static volatile SingularAttribute<ApprovalStatus, String> status;

}

