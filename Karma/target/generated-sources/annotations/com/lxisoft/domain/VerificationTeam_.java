package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VerificationTeam.class)
public abstract class VerificationTeam_ {

	public static volatile SetAttribute<VerificationTeam, Need> needs;
	public static volatile SetAttribute<VerificationTeam, LoggedUser> approvingUsers;
	public static volatile SingularAttribute<VerificationTeam, Long> id;
	public static volatile SingularAttribute<VerificationTeam, String> approvalLevel;

}

