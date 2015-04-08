package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.CourseRegistration;
import sust.paperlessexm.entity.Department;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Integer> studentId;
    public static volatile SingularAttribute<Student, String> password;
    public static volatile SingularAttribute<Student, String> emailAddress;
    public static volatile SingularAttribute<Student, String> registrationNo;
    public static volatile SingularAttribute<Student, Integer> session;
    public static volatile ListAttribute<Student, CourseRegistration> courseRegistrationList;
    public static volatile SingularAttribute<Student, String> studentName;
    public static volatile SingularAttribute<Student, Department> departmentId;
    public static volatile SingularAttribute<Student, String> photo;
    public static volatile SingularAttribute<Student, String> contactNo;

}