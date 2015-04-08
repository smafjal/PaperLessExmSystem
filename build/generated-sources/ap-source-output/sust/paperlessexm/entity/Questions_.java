package sust.paperlessexm.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sust.paperlessexm.entity.OptionsStores;
import sust.paperlessexm.entity.QuestionsAns;
import sust.paperlessexm.entity.QuestionsType;
import sust.paperlessexm.entity.Test;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-03-21T04:24:19")
@StaticMetamodel(Questions.class)
public class Questions_ { 

    public static volatile SingularAttribute<Questions, Integer> questionsId;
    public static volatile ListAttribute<Questions, OptionsStores> optionsStoresList;
    public static volatile SingularAttribute<Questions, Integer> questionNo;
    public static volatile SingularAttribute<Questions, QuestionsType> questionsTypeId;
    public static volatile SingularAttribute<Questions, Test> testId;
    public static volatile SingularAttribute<Questions, Boolean> isDirectMarking;
    public static volatile ListAttribute<Questions, QuestionsAns> questionsAnsList;
    public static volatile SingularAttribute<Questions, Integer> totalMarks;
    public static volatile SingularAttribute<Questions, String> correctAns;

}