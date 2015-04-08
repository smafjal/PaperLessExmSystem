package sust.paperlessexm.Main;

import java.util.ArrayList;
import java.util.List;
import sust.paperlessexm.api.QuestionsApi;
import sust.paperlessexm.api.impl.OptionStoresApiImpl;
import sust.paperlessexm.api.impl.QuestionsAnsApiImpl;
import sust.paperlessexm.api.impl.QuestionsApiImpl;
import sust.paperlessexm.api.impl.QuestionsTypeApiImpl;
import sust.paperlessexm.api.impl.TeachesApiImpl;
import sust.paperlessexm.api.impl.TestApiImpl;
import sust.paperlessexm.api.impl.TestRegistrationApiImpl;
import sust.paperlessexm.bean.OptionsStoresBean;
import sust.paperlessexm.bean.QuestionsAnsBean;
import sust.paperlessexm.bean.QuestionsBean;
import sust.paperlessexm.bean.QuestionsTypeBean;
import sust.paperlessexm.bean.TeachesBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.bean.TestRegistrationBean;
import sust.paperlessexm.entity.Teaches;
import sust.paperlessexm.exception.GenericBusinessException;
import sust.paperlessexm.session.TeachesService;

/**
 *
 * @author Sm19
 */
public class UpdateTwo {

    public static void main(String args[]) throws GenericBusinessException {

        //-------- update questions ---- //
        /*
         TestApiImpl testApiImpl = new TestApiImpl();
         QustionsApiImpl questionsApiImpl = new QustionsApiImpl();
         QuestionsTypeApiImpl questionsTypeApiImpl = new QuestionsTypeApiImpl();

         List<TestBean> testBeanist = testApiImpl.getTestList();
         List<QuestionsTypeBean> questionsTypeBeanList = questionsTypeApiImpl.getQuestionsTypeList();

         String correct = "abcAns0";

         for (int i = 1; i < 10; i++) {

         QuestionsBean questionsBean = new QuestionsBean();
         questionsBean.setTotalMarks(i + 2);
         QuestionsTypeBean questionsTypeBean = questionsTypeBeanList.get(i % 5);
         questionsBean.setQuestionsTypeId(questionsTypeBean);
         questionsBean.setCorrectAns(correct);

         if (questionsTypeBean.getNoOfOptions() == 1) {
         questionsBean.setIsDirectMarking(false);
         } else {
         questionsBean.setIsDirectMarking(true);
         }
         questionsBean.setTestId(testBeanist.get(i));

         for (int j = 1; j < 5; j++) {
                
         questionsBean.setQuestionNo(j);
         questionsApiImpl.addQuestions(questionsBean);
         }
         }
         */
        // ----------- update options Stores ----------//
        /*
         QustionsApiImpl questionsApiImpl = new QustionsApiImpl();
         OptionStoresApiImpl optionStoresApiImpl = new OptionStoresApiImpl();

         List<QuestionsBean> questionsBeanList = questionsApiImpl.getQuestionsList();

         for (int i = 1; i < questionsBeanList.size(); i++) {

         OptionsStoresBean optionsStoresBean = new OptionsStoresBean();

         QuestionsBean questionsBean = questionsBeanList.get(i);
         int num = questionsBean.getQuestionsTypeId().getNoOfOptions();

         for (int j = 1; j <= num; j++) {
         String option = "abcAns" + Integer.toString(j);
         optionsStoresBean.setOptionsText(option);
         optionsStoresBean.setQuestionsId(questionsBeanList.get(i));
                
         optionStoresApiImpl.addOptionsStores(optionsStoresBean);
         }
         }
         */
        // --------- update QuestionAns ---------------//
        /*
         QuestionsAnsApiImpl questionsAnsApiImpl = new QuestionsAnsApiImpl();
         TestRegistrationApiImpl testRegistrationApiImpl = new TestRegistrationApiImpl();
         QuestionsApiImpl questionsApiImpl = new QuestionsApiImpl();

         List<QuestionsBean> questionsBeanList = questionsApiImpl.getQuestionsList();
         List<TestRegistrationBean> testRegistrationBeanList = testRegistrationApiImpl.getTestRegistrationList();

         for (int i = 2; i < 20; i++) {

         QuestionsAnsBean questionsAnsBean = new QuestionsAnsBean();
         QuestionsBean questionsBean = questionsBeanList.get(i);
         questionsAnsBean.setQuestionsId(questionsBean);
         if (i % 2 == 0) {
         questionsAnsBean.setGivenMarks(questionsBean.getTotalMarks());
         } else {
         questionsAnsBean.setGivenMarks(questionsBean.getTotalMarks() - 1);
         }
         questionsAnsBean.setGivenAns(questionsBean.getCorrectAns());
         questionsAnsBean.setTestRegistrationId(testRegistrationBeanList.get(0));

         questionsAnsApiImpl.addQuestionsAns(questionsAnsBean);
         }
         */
        /*
         TeachesApiImpl teachesApiImpl =new TeachesApiImpl();
         ArrayList<TeachesBean> list=(ArrayList<TeachesBean>) teachesApiImpl.getTeachesList();
         TeachesBean teachesBean=list.get(list.size()-1);
         teachesApiImpl.deleteTeaches(teachesBean.getTeachesId());
         */
        /*
        
         // delete some data.....
         /*
         TeachesService teachesService = new TeachesService();
         ArrayList<Teaches> list2 = (ArrayList<Teaches>) teachesService.getTeachesList();
         Teaches teaches = list2.get(list2.size() - 1);
         for (int i = 0; i < 20; i++) {
         teachesService.deleteTeaches(list2.get(i).getTeachesId());
         }
         */
        /*
         // test
         TeachesApiImpl teachesApiImpl =new TeachesApiImpl();
         Integer teachersId=32 ,courseId=28;
         System.out.println("======= OK ========");
         TeachesBean teachesBean =teachesApiImpl.CheckTeaches(teachersId, courseId);
        
         System.out.println("TeachesBean: Id: "+teachesBean.getTeachesId()+" teachersId: "+teachesBean.getTeachersId().getTeachersName()+" CourseId: "+teachesBean.getCourseId().getCourseCode());
                
         */
        
        
        // Add test testing.....
        TestApiImpl testApiImpl =new TestApiImpl();
        TestBean testBean =new TestBean();
        
                
                
        /*
        
        
        */
        
   }
}
