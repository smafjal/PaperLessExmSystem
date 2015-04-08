package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Questions;
import sust.paperlessexm.entity.TestRegistration;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(QuestionsAns.class)
public class QuestionsAns_ { 

    public static volatile SingularAttribute<QuestionsAns, Questions> questionsId;
    public static volatile SingularAttribute<QuestionsAns, TestRegistration> testRegistrationId;
    public static volatile SingularAttribute<QuestionsAns, Integer> givenMarks;
    public static volatile SingularAttribute<QuestionsAns, Integer> questionsAnsId;
    public static volatile SingularAttribute<QuestionsAns, String> givenAns;

}