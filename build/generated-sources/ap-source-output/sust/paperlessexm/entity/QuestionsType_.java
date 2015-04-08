package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.Questions;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(QuestionsType.class)
public class QuestionsType_ { 

    public static volatile SingularAttribute<QuestionsType, Integer> noOfOptions;
    public static volatile SingularAttribute<QuestionsType, Integer> questionsTypeId;
    public static volatile ListAttribute<QuestionsType, Questions> questionsList;
    public static volatile SingularAttribute<QuestionsType, String> typeName;

}