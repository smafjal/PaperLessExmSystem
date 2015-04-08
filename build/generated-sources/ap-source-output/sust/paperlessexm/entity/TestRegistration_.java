package sust.paperlessexm.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.CourseRegistration;
import sust.paperlessexm.entity.QuestionsAns;
import sust.paperlessexm.entity.Test;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(TestRegistration.class)
public class TestRegistration_ { 

    public static volatile SingularAttribute<TestRegistration, Integer> testRegistrationId;
    public static volatile SingularAttribute<TestRegistration, Date> submitTime;
    public static volatile SingularAttribute<TestRegistration, Integer> testMarks;
    public static volatile SingularAttribute<TestRegistration, Date> startTime;
    public static volatile SingularAttribute<TestRegistration, Test> testId;
    public static volatile ListAttribute<TestRegistration, QuestionsAns> questionsAnsList;
    public static volatile SingularAttribute<TestRegistration, CourseRegistration> courseRegistrationId;

}