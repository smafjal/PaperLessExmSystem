package sust.paperlessexm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sust.paperlessexm.api.impl.CourseRegistrationApiImpl;
import sust.paperlessexm.api.impl.TeachesApiImpl;
import sust.paperlessexm.api.impl.TestApiImpl;
import sust.paperlessexm.api.impl.TestRegistrationApiImpl;
import sust.paperlessexm.bean.CourseRegistrationBean;
import sust.paperlessexm.bean.StudentBean;
import sust.paperlessexm.bean.TeachersBean;
import sust.paperlessexm.bean.TeachesBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.bean.TestRegistrationBean;
import sust.paperlessexm.exception.GenericBusinessException;
import sust.paperlessexm.tool.DateCheck;

/**
 *
 * @author Sm19
 */
public class TestEntryVerification extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, GenericBusinessException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        StudentBean studentBean;
        TeachersBean teachersBean;

        String testIdStr = (String) request.getParameter("testId");
        int testId = Integer.parseInt(testIdStr);
        int id = (Integer) session.getAttribute("id");
        TestApiImpl testApiImpl = new TestApiImpl();
        TestBean bean = testApiImpl.findTestByTestId(testId).get(0);
        String testStatus = DateCheck.dateStatus(new Date(), bean.getTestTime(), bean.getTestDuration());

        testStatus = "Running";

        if ("Running".equals(testStatus)) {

            // for student...
            if (id == 0) {

                // check student registration for this particular test....
                boolean valid = true;

                studentBean = (StudentBean) session.getAttribute("user");
                int studentId = studentBean.getStudentId();
                
                CourseRegistrationApiImpl courseRegistrationApiImpl = new CourseRegistrationApiImpl();
         
                // CourseRegistration valid....
                List<CourseRegistrationBean> cr = courseRegistrationApiImpl.findCourseRegistrationByCourseRegistrationId(bean.getCourseId().getCourseId());
                CourseRegistrationBean courseRegistrationBean = new CourseRegistrationBean();

                if (cr.isEmpty()) {
                    valid = false;
                }

                if (valid) {
                    for (int i = 0; i < cr.size(); i++) {
                        courseRegistrationBean = cr.get(i);
                        if (Objects.equals(courseRegistrationBean.getStudentId().getStudentId(), studentId) && courseRegistrationBean.getIsApproved()) {
                            session.setAttribute("testId", testId);
                            RequestDispatcher rd = request.getRequestDispatcher("/exam/index.jsp");
                            rd.forward(request, response);
                        }
                    }
                    response.sendRedirect("dashboard/index.jsp");
                } else {
                    response.sendRedirect("dashboard/index.jsp");
                }

            } else if (id == 1) { // for teacher...

                // check teacher  for this particular test..
                boolean valid = true;
                teachersBean = (TeachersBean) session.getAttribute("user");
                TeachesApiImpl teachesApiImpl = new TeachesApiImpl();

                List<TestBean> tb = testApiImpl.findTestByTestId(testId);
                TestBean testBean = new TestBean();
                if (tb.isEmpty()) {
                    valid = false;
                } else {
                    testBean = tb.get(0);
                }

                // check teacher to teaches this course
                TeachesBean teachesBean = new TeachesBean();
                List<TeachesBean> teb = null;
                if (valid) {
                    teb = teachesApiImpl.findTeachesByTeachersId(teachersBean.getTeachersId());
                    if (teb.isEmpty()) {
                        valid = false;
                    }
                }
                if (valid) {
                    teachesBean = teb.get(0);
                }
                if (valid) {

                    if (Objects.equals(testBean.getCourseId().getCourseId(), teachesBean.getCourseId().getCourseId())) {
                        session.setAttribute("testId", testId);

                        //out.println("<h1>Pailam</h1>");
                        RequestDispatcher rd = request.getRequestDispatcher("/exam/index.jsp");
                        rd.forward(request, response);
                    } else {
                        response.sendRedirect("dashboard/index.jsp");
                    }
                } else {
                    response.sendRedirect("dashboard/index.jsp");
                }
            }
        } else {
            response.sendRedirect("dashboard/index.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (GenericBusinessException ex) {
            Logger.getLogger(TestEntryVerification.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (GenericBusinessException ex) {
            Logger.getLogger(TestEntryVerification.class.getName()).log(Level.SEVERE, null, ex);
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
