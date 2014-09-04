package org.mat.nounou.model;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class People_ {
	//metamodel data
    public static volatile SingularAttribute<People, Boolean> anonymous;
    public static volatile SingularAttribute<People, String> username;
    public static volatile SingularAttribute<People, Sexes> sex;
    public static volatile CollectionAttribute<People, People> peopleCollection1;
    public static volatile SingularAttribute<People, String> privateKey;
    public static volatile SingularAttribute<People, String> picUrl1;
    public static volatile SingularAttribute<People, String> name;
    public static volatile SingularAttribute<People, Boolean> active;
    public static volatile SingularAttribute<People, Sexes> sexualPreference;
    public static volatile SingularAttribute<People, Long> positiveRatings;
    public static volatile SingularAttribute<People, String> password;
    public static volatile CollectionAttribute<People, People> peopleCollection;
}
