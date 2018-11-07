package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Media.class)
public abstract class Media_ {

	public static volatile SingularAttribute<Media, String> fileName;
	public static volatile SingularAttribute<Media, String> extension;
	public static volatile SingularAttribute<Media, Need> need;
	public static volatile SingularAttribute<Media, Long> id;
	public static volatile SingularAttribute<Media, String> url;

}

