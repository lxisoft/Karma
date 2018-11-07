package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, Long> zip;
	public static volatile SingularAttribute<Address, String> houseName;
	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, LoggedUser> loggedUser;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> place;
	public static volatile SingularAttribute<Address, String> state;

}

