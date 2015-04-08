package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Course;
import sust.paperlessexm.entity.Student;
import sust.paperlessexm.entity.TestRegistration;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(CourseRegistration.class)
public class CourseRegistration_ { 

    public static volatile SingularAttribute<CourseRegistration, Student> studentId;
    public static volatile SingularAttribute<CourseRegistration, Boolean> isApproved;
    public static volatile SingularAttribute<CourseRegistration, Integer> courseRegistrationId;
    public static volatile SingularAttribute<CourseRegistration, Course> courseId;
    public static volatile ListAttribute<CourseRegistration, TestRegistration> testRegistrationList;

}