package sust.paperlessexm.servlets;

import java.util.List;
import sust.paperlessexm.api.impl.StudentApiImpl;
import sust.paperlessexm.api.impl.TeachersApiImpl;
import sust.paperlessexm.bean.StudentBean;
import sust.paperlessexm.bean.TeachersBean;
import sust.paperlessexm.exception.GenericBusinessException;

/**
 *
 * @author Sm19
 */
public class UserLogin {

    private StudentBean studentBean = new StudentBean();
    private TeachersBean teachersBean = new TeachersBean();
    private String userName;
    private String passWord;
    private int id = -1; // student=0 && teacher=1

    public UserLogin(String userName, String passWord, int id) {
        this.userName = userName;
        this.passWord = passWord;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public TeachersBean getTeachersBean() {
        return teachersBean;
    }

    // change here for login purpose :: id=0(Student) && id=1(Teachers)
    public boolean login() throws GenericBusinessException {

        if (userName.isEmpty() || passWord.isEmpty() || id == -1) {
            return false;
        }
        if (id == 0) {
            
            StudentApiImpl apiImpl = new StudentApiImpl();
            List<StudentBean> stList = apiImpl.getStudentList();
            
            for (int i = 0; i < stList.size(); i++) {

                String regNo = stList.get(i).getRegistrationNo();
                String listStudentpass = stList.get(i).getPassword();

                if (regNo.equals(userName) && listStudentpass.equals(passWord)) {
                    this.studentBean = stList.get(i);
                    return true;
                }
            }
        } else if (id == 1) {
            
            TeachersApiImpl teachersApiImpl = new TeachersApiImpl();
            List<TeachersBean> teList = teachersApiImpl.getTeachersList();

            for (int i = 0; i < teList.size(); i++) {

                String employeeCode = teList.get(i).getEmployeeCode();
                String listTeacherstpass = teList.get(i).getPassword();

                if (employeeCode.equals(userName) && listTeacherstpass.equals(passWord)) {
                    this.teachersBean = teList.get(i);
                    return true;
                }
            }
        }
        return false;
    }
}
