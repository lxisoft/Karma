package com.lxisoft.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Need.class)
public abstract class Need_ {

	public static volatile SingularAttribute<Need, Instant> date;
	public static volatile SingularAttribute<Need, Severity> severity;
	public static volatile SingularAttribute<Need, ApprovalStatus> approvalStatus;
	public static volatile SingularAttribute<Need, LoggedUser> postedUser;
	public static volatile SingularAttribute<Need, String> beneficiaryType;
	public static volatile SetAttribute<Need, Media> proofs;
	public static volatile SingularAttribute<Need, VerificationTeam> verificationTeam;
	public static volatile SingularAttribute<Need, String> description;
	public static volatile SingularAttribute<Need, Long> id;
	public static volatile SetAttribute<Need, Help> helps;
	public static volatile SetAttribute<Need, Category> categories;
	public static volatile SetAttribute<Need, UserCheck> userChecks;

}

