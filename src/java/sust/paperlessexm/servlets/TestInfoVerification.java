/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sust.paperlessexm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sust.paperlessexm.api.impl.CourseApiImpl;
import sust.paperlessexm.api.impl.CourseRegistrationApiImpl;
import sust.paperlessexm.api.impl.TeachesApiImpl;
import sust.paperlessexm.api.impl.TestApiImpl;
import sust.paperlessexm.api.impl.TestRegistrationApiImpl;
import sust.paperlessexm.bean.CourseBean;
import sust.paperlessexm.bean.CourseRegistrationBean;
import sust.paperlessexm.bean.TeachersBean;
import sust.paperlessexm.bean.TeachesBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.bean.TestRegistrationBean;
import sust.paperlessexm.exception.GenericBusinessException;
import sust.paperlessexm.tool.DateTools;

/**
 *
 * @author Sm19
 */
public class TestInfoVerification extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, GenericBusinessException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //out.println("Debug 01");
        HttpSession session = request.getSession(true);

        String courseCode, testTitle, testDescription;
        int courseSession, testId, marks, testDuration, testNo;
        Date testTime = new Date();

        TeachesBean teachesId = new TeachesBean();
        CourseBean courseId = new CourseBean();
        CourseApiImpl courseApiImpl = new CourseApiImpl();
        TeachesApiImpl teachesApiImpl = new TeachesApiImpl();
        TestApiImpl testApiImpl = new TestApiImpl();
        boolean allValid = true;

        testTitle = request.getParameter("testTitle");
        courseCode = request.getParameter("courseCode");
        courseSession = Integer.parseInt(request.getParameter("courseSession"));
        testTime = DateTools.toDate(request.getParameter("testDate"), request.getParameter("testTime"));
        int hour = Integer.parseInt(request.getParameter("testHour"));
        int min = Integer.parseInt(request.getParameter("testMin"));
        testDuration = (hour * 60) + min;
        marks = Integer.parseInt(request.getParameter("testMarks"));
        testDescription = request.getParameter("testDescription");

        // -----  bean obj for validation 
        courseId = courseApiImpl.checkCourse(courseCode, courseSession);
        TeachersBean teachersBean = (TeachersBean) session.getAttribute("user");

        //out.println("Debug 02");
        if (courseId.getPrimaryKey() == null) {
            allValid = false;
        }

        //out.println("Debug 03");
        if (allValid) {
            teachesId = teachesApiImpl.CheckTeaches(teachersBean.getTeachersId(), courseId.getCourseId());
            if (teachesId.getPrimaryKey() == null) {
                allValid = false;
            }
        }

        //out.println("Debug 04");
        //allValid = false;
        if (allValid) {

            //System.out.println("Te ID: " + teachesId.getTeachesId());
            //out.println("Inside Debug 05");
            testNo = testApiImpl.findTestNo(courseId.getCourseId());
            TestBean testSet = new TestBean();

            //TestSet testSet = new TestSet();
            testSet.setCourseId(courseId);
            testSet.setMarks(marks);
            testSet.setTestTitle(testTitle);
            testSet.setTestTime(testTime);
            testSet.setTestDuration(testDuration);
            testSet.setDescription(testDescription);
            testSet.setTeachesId(teachesId);
            testSet.setTestNo(testNo);
            testSet.setUpdateDate(new Date());
            testSet.setCreateDate(new Date());

            session.setAttribute("testInfo", testSet);
            response.sendRedirect("TestSetInfo");

        } else {
            response.sendRedirect("AddTestAndQuestions/index.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TestInfoVerification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GenericBusinessException ex) {
            Logger.getLogger(TestInfoVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TestInfoVerification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GenericBusinessException ex) {
            Logger.getLogger(TestInfoVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
