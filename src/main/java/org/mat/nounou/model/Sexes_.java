package org.mat.nounou.model;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class Sexes_ {
    public static volatile SingularAttribute<Sexes, String> symbol;
    public static volatile CollectionAttribute<Sexes, People> peopleCollection1;
    public static volatile SingularAttribute<Sexes, String> name;
    public static volatile CollectionAttribute<Sexes, People> peopleCollection;
}
