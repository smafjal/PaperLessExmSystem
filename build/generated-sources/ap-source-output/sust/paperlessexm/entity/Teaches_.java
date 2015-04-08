package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Course;
import sust.paperlessexm.entity.Teachers;
import sust.paperlessexm.entity.Test;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Teaches.class)
public class Teaches_ { 

    public static volatile SingularAttribute<Teaches, Integer> teachesId;
    public static volatile ListAttribute<Teaches, Test> testList;
    public static volatile SingularAttribute<Teaches, Teachers> teachersId;
    public static volatile SingularAttribute<Teaches, Course> courseId;

}