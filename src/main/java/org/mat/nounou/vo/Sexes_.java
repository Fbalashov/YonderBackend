package org.mat.nounou.vo;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.mat.nounou.model.People;
import org.mat.nounou.model.Sexes;

public class Sexes_ {
    public static volatile SingularAttribute<Sexes, String> symbol;
    public static volatile CollectionAttribute<Sexes, People> peopleCollection1;
    public static volatile SingularAttribute<Sexes, String> name;
    public static volatile CollectionAttribute<Sexes, People> peopleCollection;
}
