package sust.paperlessexm.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Course;
import sust.paperlessexm.entity.Questions;
import sust.paperlessexm.entity.Teaches;
import sust.paperlessexm.entity.TestRegistration;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile SingularAttribute<Test, Date> updateDate;
    public static volatile SingularAttribute<Test, Integer> testNo;
    public static volatile SingularAttribute<Test, String> description;
    public static volatile SingularAttribute<Test, Integer> marks;
    public static volatile SingularAttribute<Test, String> testTitle;
    public static volatile SingularAttribute<Test, Teaches> teachesId;
    public static volatile ListAttribute<Test, Questions> questionsList;
    public static volatile SingularAttribute<Test, Integer> testId;
    public static volatile SingularAttribute<Test, Date> testTime;
    public static volatile SingularAttribute<Test, Course> courseId;
    public static volatile SingularAttribute<Test, Integer> testDuration;
    public static volatile SingularAttribute<Test, Date> createDate;
    public static volatile ListAttribute<Test, TestRegistration> testRegistrationList;

}