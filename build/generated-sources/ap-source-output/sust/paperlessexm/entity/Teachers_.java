package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Teaches;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Teachers.class)
public class Teachers_ { 

    public static volatile SingularAttribute<Teachers, Integer> teachersId;
    public static volatile SingularAttribute<Teachers, String> emailAddress;
    public static volatile SingularAttribute<Teachers, String> password;
    public static volatile ListAttribute<Teachers, Teaches> teachesList;
    public static volatile SingularAttribute<Teachers, String> teachersDept;
    public static volatile SingularAttribute<Teachers, String> teachersName;
    public static volatile SingularAttribute<Teachers, String> photo;
    public static volatile SingularAttribute<Teachers, String> contactNo;
    public static volatile SingularAttribute<Teachers, String> employeeCode;

}