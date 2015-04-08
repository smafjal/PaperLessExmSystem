package sust.paperlessexm.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import sust.paperlessexm.api.impl.CourseApiImpl;
import sust.paperlessexm.api.impl.CourseRegistrationApiImpl;
import sust.paperlessexm.api.impl.QuestionsTypeApiImpl;
import sust.paperlessexm.api.impl.StudentApiImpl;
import sust.paperlessexm.api.impl.TeachesApiImpl;
import sust.paperlessexm.api.impl.TestApiImpl;
import sust.paperlessexm.api.impl.TestRegistrationApiImpl;
import sust.paperlessexm.bean.CourseBean;
import sust.paperlessexm.bean.CourseRegistrationBean;
import sust.paperlessexm.bean.QuestionsTypeBean;
import sust.paperlessexm.bean.StudentBean;
import sust.paperlessexm.bean.TeachesBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.bean.TestRegistrationBean;
import sust.paperlessexm.exception.GenericBusinessException;

/**
 *
 * @author Sm19
 */
public class UpdateOne {

    public static void main(String ars[]) throws GenericBusinessException, ParseException {

        System.out.println("UpdateOne Start Now!!");
        /*
         // --------- Update CourseRegistration ---------------
         StudentApiImpl studentApiImpl = new StudentApiImpl();
         CourseApiImpl courseApiImpl = new CourseApiImpl();
         CourseRegistrationApiImpl courseRegistrationApiImpl = new CourseRegistrationApiImpl();

         List<StudentBean> studentBeanList = studentApiImpl.getStudentList();
         List<CourseBean> courseBeanList = courseApiImpl.getCourseList();

         for (int i = 1; i < 10; i++) {
         for (int j = 1; j < 10; j++) {

         CourseRegistrationBean courseRegistrationBean = new CourseRegistrationBean();
         courseRegistrationBean.setCourseId(courseBeanList.get(i));
         courseRegistrationBean.setIsApproved(true);
         courseRegistrationBean.setStudentId(studentBeanList.get(j));
         courseRegistrationBean.setCourseRegistrationId(null);
                
         courseRegistrationApiImpl.addCourseRegistration(courseRegistrationBean);
         }
         }
         */

        // ------------ Update Test ------------//
        TestApiImpl testApiImpl = new TestApiImpl();
        CourseApiImpl courseApiImpl = new CourseApiImpl();
        TeachesApiImpl teachesApiImpl = new TeachesApiImpl();

        List<CourseBean> courseBeanList = courseApiImpl.getCourseList();
        List<TeachesBean> teachesBeanList = teachesApiImpl.getTeachesList();

        String title[] = {"one", "two", "three", "four", "five", "six"};

        for (int i = 24; i < courseBeanList.size(); i++) {
            for (int j = 22; j < teachesBeanList.size(); j++) {

                TestBean testBean = new TestBean();
                testBean.setTestId(null);
                testBean.setMarks(50 + i * j);
                String t = "PaperLessExmSystem Testing " + title[(i + j) % 6];
                testBean.setTestTitle(t);

                String tmp = Integer.toString(i % 30) + "/" + Integer.toString(j % 12) + "/" + Integer.toString(2000 + i);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                Date date = sdf.parse(tmp);
                testBean.setCreateDate(date);
                testBean.setUpdateDate(date);

                String time = tmp + " 10:00:00";
                SimpleDateFormat sdfTime = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                Date dateTime = sdfTime.parse(time);
                testBean.setTestTime(dateTime);

                testBean.setTestDuration(3);
                testBean.setDescription("Des " + t);
                testBean.setTestNo(1);
                testBean.setCourseId(courseBeanList.get(i));
                testBean.setTeachesId(teachesBeanList.get(j));

                testApiImpl.addTest(testBean);
            }
        }

        // ------- update question_type --------//
        /*
         QuestionsTypeApiImpl questionsTypeApiImpl =new QuestionsTypeApiImpl();
        
         for(int i=1;i<=10;i++) {
            
         QuestionsTypeBean questionsTypeBean=new QuestionsTypeBean();
         questionsTypeBean.setQuestionsTypeId(null);
         questionsTypeBean.setNoOfOptions(i);
         String optionName="Option"+Integer.toString(i);
         questionsTypeBean.setTypeName(optionName);
            
         questionsTypeApiImpl.addQuestionsType(questionsTypeBean);
         }
         */
        // ------------- update Test_registration --------------//
        /*
         TestApiImpl testApiImpl = new TestApiImpl();
         CourseRegistrationApiImpl courseRegistrationApiImpl = new CourseRegistrationApiImpl();
         TestRegistrationApiImpl testRegistrationApiImpl =new TestRegistrationApiImpl();

         List<TestBean> testBeanList = testApiImpl.getTestList();
         List<CourseRegistrationBean> courseRegistrationBeanList = courseRegistrationApiImpl.getCourseRegistrationList();

         for (int i = 1; i < 12; i++) {
         for (int j = 1; j < 12; j++) {
         TestRegistrationBean testRegistrationBean = new TestRegistrationBean();
         testRegistrationBean.setCourseRegistrationId(null);
                
         String tmp = Integer.toString(i % 20) + "/" + Integer.toString(j % 6) + "/" + Integer.toString(2000 + i);
         String time = tmp + " 10:00:00";
         SimpleDateFormat sdfTime = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
         Date dateTime = sdfTime.parse(time);
         testRegistrationBean.setStartTime(dateTime);
                
         tmp = Integer.toString(i % 20+3) + "/" + Integer.toString(j % 6) + "/" + Integer.toString(2000 + i);
         time = tmp + " 10:00:00";
         sdfTime = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
         dateTime = sdfTime.parse(time);
         testRegistrationBean.setSubmitTime(dateTime);
                
         testRegistrationBean.setTestId(testBeanList.get(i));
         testRegistrationBean.setCourseRegistrationId(courseRegistrationBeanList.get(j));
                
         testRegistrationApiImpl.addTestRegistration(testRegistrationBean);
                
         }
         }
         */
        //-------- Update Question ------//
        System.out.println("UpdateOne End Here!?");
    }
}
