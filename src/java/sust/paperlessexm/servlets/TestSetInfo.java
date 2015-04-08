/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sust.paperlessexm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import sust.paperlessexm.api.impl.CourseRegistrationApiImpl;
import sust.paperlessexm.api.impl.TestApiImpl;
import sust.paperlessexm.api.impl.TestRegistrationApiImpl;
import sust.paperlessexm.bean.CourseRegistrationBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.bean.TestRegistrationBean;
import sust.paperlessexm.bean.TestSet;
import sust.paperlessexm.entity.Course;
import sust.paperlessexm.exception.GenericBusinessException;

/**
 *
 * @author Sm19
 */
public class TestSetInfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, GenericBusinessException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        TestBean testSet = (TestBean) session.getAttribute("testInfo");
        TestApiImpl testApiImpl = new TestApiImpl();

        PrintWriter out = response.getWriter();

        // sava in database
        testSet = testApiImpl.addTest(testSet);

        // add in session for further access in another page
        session.setAttribute("testInfo", testSet);
        response.sendRedirect("AddTestAndQuestions/addQuestions.jsp");
        //RequestDispatcher rd = request.getRequestDispatcher("/AddTestAndQuestions/addQuestions.jsp");
        //rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (GenericBusinessException ex) {
            Logger.getLogger(TestSetInfo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TestSetInfo.class.getName()).log(Level.SEVERE, null, ex);
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
