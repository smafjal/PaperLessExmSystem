package sust.paperlessexm.Main;

import com.finalist.util.log.LogService;
import com.finalist.util.log.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import sust.paperlessexm.api.impl.CourseApiImpl;
import sust.paperlessexm.api.impl.StudentApiImpl;
import sust.paperlessexm.api.impl.TeachersApiImpl;
import sust.paperlessexm.api.impl.TestApiImpl;
import sust.paperlessexm.bean.CourseBean;
import sust.paperlessexm.bean.StudentBean;
import sust.paperlessexm.bean.TeachersBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.entity.Student;
import sust.paperlessexm.exception.GenericBusinessException;
import sust.paperlessexm.hibernatehelper.HibernateQueryHelper;
import static sust.paperlessexm.hibernatehelper.HibernateSession.getSession;
import sust.paperlessexm.session.StudentService;

/**
 *
 * @author Sm19
 */
public class Main {

    public static void main(String args[]) throws GenericBusinessException {
        
        TestApiImpl testApiImpl = new TestApiImpl();
        CourseApiImpl courseApiImpl = new CourseApiImpl();
        TeachersApiImpl teachersApiImpl = new TeachersApiImpl();

        List<TestBean> testBeanList = testApiImpl.getTestList();
        //List<CourseBean>courseBeans =courseApiImpl.getCourseList();
        //List<TeachersBean> teachersBeans=teachersApiImpl.getTeachersList();

        Collections.sort(testBeanList, new Comparator<TestBean>() {
            @Override
            public int compare(TestBean a, TestBean b) {

                Date d1 = a.getTestTime();
                Date d2 = b.getTestTime();

                if (d1.before(d2)) {
                    return 1;
                } else if (d1.equals(d2)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        
        System.out.println("Start: "+testBeanList.size());
        
        
        for (int i = 0; i < Math.min(50,testBeanList.size()); i++) {

            TestBean testBean = testBeanList.get(i);
            CourseBean courseBean = courseApiImpl.findCourseByCourseId(testBean.getCourseId().getCourseId()).get(0);
            TeachersBean teachersBean = teachersApiImpl.findTeachersByTeachersId(testBean.getTeachesId().getTeachersId().getTeachersId()).get(0);

            String exmDescription = " HHH ";

            exmDescription = "Time: " + testBean.getTestTime() + " Length: " + testBean.getTestDuration();
            exmDescription=exmDescription+" CourseCode: "+courseBean.getCourseCode();
            exmDescription=exmDescription+" Session: "+courseBean.getSession();
            exmDescription=exmDescription+" Dept: "+courseBean.getAcceptingDept();
            exmDescription=exmDescription+" Teacher: "+teachersBean.getTeachersName();
            exmDescription=exmDescription+" Title: "+testBean.getTestTitle();
            System.out.println("I: "+i+" -- "+exmDescription);
        }
        System.out.println("The End Of this Experiment! Journy by: "+testBeanList.size()+ " Elements");
    }
}
