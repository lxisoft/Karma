package com.lxisoft.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoggedUser.class)
public abstract class LoggedUser_ {

	public static volatile SingularAttribute<LoggedUser, String> profession;
	public static volatile SetAttribute<LoggedUser, Need> needs;
	public static volatile SingularAttribute<LoggedUser, String> lastName;
	public static volatile SetAttribute<LoggedUser, Address> addresses;
	public static volatile SingularAttribute<LoggedUser, String> gender;
	public static volatile SingularAttribute<LoggedUser, Media> profilePic;
	public static volatile SingularAttribute<LoggedUser, Long> rating;
	public static volatile SingularAttribute<LoggedUser, String> description;
	public static volatile SetAttribute<LoggedUser, VerificationTeam> verificationTeams;
	public static volatile SingularAttribute<LoggedUser, String> firstName;
	public static volatile SingularAttribute<LoggedUser, String> bloodGroup;
	public static volatile SingularAttribute<LoggedUser, LocalDate> dob;
	public static volatile SingularAttribute<LoggedUser, Long> id;
	public static volatile SetAttribute<LoggedUser, Help> helps;
	public static volatile SingularAttribute<LoggedUser, String> email;

}

