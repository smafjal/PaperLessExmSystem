package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Student;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> deptName;
    public static volatile SingularAttribute<Department, Integer> departmentId;
    public static volatile ListAttribute<Department, Student> studentList;
    public static volatile SingularAttribute<Department, String> deptCode;

}