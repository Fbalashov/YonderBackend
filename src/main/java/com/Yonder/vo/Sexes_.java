package com.Yonder.vo;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;

import com.Yonder.model.People;
import com.Yonder.model.Sexes;

public class Sexes_ {
    public static volatile SingularAttribute<Sexes, String> symbol;
    public static volatile CollectionAttribute<Sexes, People> peopleCollection1;
    public static volatile SingularAttribute<Sexes, String> name;
    public static volatile CollectionAttribute<Sexes, People> peopleCollection;
}
