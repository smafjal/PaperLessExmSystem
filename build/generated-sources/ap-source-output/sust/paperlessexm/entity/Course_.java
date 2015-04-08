package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.CourseRegistration;
import sust.paperlessexm.entity.Teaches;
import sust.paperlessexm.entity.Test;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> courseTitle;
    public static volatile ListAttribute<Course, Test> testList;
    public static volatile SingularAttribute<Course, String> acceptingDept;
    public static volatile SingularAttribute<Course, String> offeringDept;
    public static volatile SingularAttribute<Course, Integer> session;
    public static volatile ListAttribute<Course, Teaches> teachesList;
    public static volatile ListAttribute<Course, CourseRegistration> courseRegistrationList;
    public static volatile SingularAttribute<Course, String> courseCode;
    public static volatile SingularAttribute<Course, Integer> semester;
    public static volatile SingularAttribute<Course, Double> credit;
    public static volatile SingularAttribute<Course, Integer> courseId;

}