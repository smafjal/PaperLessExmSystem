/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sust.paperlessexm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sust.paperlessexm.api.impl.OptionStoresApiImpl;
import sust.paperlessexm.api.impl.QuestionsAnsApiImpl;
import sust.paperlessexm.api.impl.QuestionsApiImpl;
import sust.paperlessexm.api.impl.QuestionsTypeApiImpl;
import sust.paperlessexm.bean.OptionsStoresBean;
import sust.paperlessexm.bean.QuestionsAnsBean;
import sust.paperlessexm.bean.QuestionsBean;
import sust.paperlessexm.bean.QuestionsTypeBean;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.exception.GenericBusinessException;

/**
 *
 * @author Sm19
 */
public class QuestionsAdd extends HttpServlet {

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
            throws ServletException, IOException, GenericBusinessException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        String questionTitle = request.getParameter("title");
        //out.println("Title: " + questionTitle);

        String optionStr = request.getParameter("options");
        //out.println("Options: " + optionStr);

        StringTokenizer tk = new StringTokenizer(optionStr, ",");
        ArrayList<String> options = new ArrayList<>();
        while (tk.hasMoreTokens()) {
            options.add(tk.nextToken());
        }

//        for (int i = 0; i < options.size(); i++) {
//            out.println("OP: " + options.get(i));
//        }
        QuestionsBean questionsBean = new QuestionsBean();
        questionsBean.setTotalMarks(Integer.parseInt(options.get(options.size() - 1)));

        if (options.size() > 1) {
            questionsBean.setIsDirectMarking(true);
            questionsBean.setCorrectAns(options.get(0));
        } else {
            questionsBean.setIsDirectMarking(false);
            questionsBean.setCorrectAns("");
        }
        
        QuestionsTypeApiImpl questionsTypeApiImpl = new QuestionsTypeApiImpl();

        questionsBean.setQuestionsTypeId((QuestionsTypeBean) (questionsTypeApiImpl.findQuestionsTypeByQuestionsTypeId(options.size())).get(0));
        TestBean testSet = (TestBean) session.getAttribute("testInfo");

        //out.println("T_title: "+testSet.getTestTitle()+" marks: "+testSet.getMarks());
        //out.println("TestID: "+testSet.getTestId());
        questionsBean.setTestId(testSet);

        QuestionsApiImpl questionsApiImpl = new QuestionsApiImpl();
        int question_No = questionsApiImpl.findQuestionsByTestId(testSet.getTestId()).size() + 1;
        questionsBean.setQuestionNo(question_No);

        //out.println("ASLAM");
        questionsBean = questionsApiImpl.addQuestions(questionsBean);
        //out.println("Question ID: " + questionsBean.getPrimaryKey() + "Q_No: " + questionsBean.getQuestionNo());
        OptionStoresApiImpl optionStoresApiImpl = new OptionStoresApiImpl();

        OptionsStoresBean optionsStoresBean = new OptionsStoresBean();
        questionTitle = "title:" + questionTitle;
        optionsStoresBean.setOptionsText(questionTitle);
        optionsStoresBean.setQuestionsId(questionsBean);
        optionStoresApiImpl.addOptionsStores(optionsStoresBean);

        for (int i = 1; i < options.size() - 1; i++) {

            optionsStoresBean.setOptionsText(options.get(i));
            optionsStoresBean.setQuestionsId(questionsBean);
            optionStoresApiImpl.addOptionsStores(optionsStoresBean);

        }
        
        out.println("Test ID: " + testSet.getTestId() + " QID: " + questionsBean.getQuestionsId() + " Added Question: " + question_No);

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
        } catch (GenericBusinessException ex) {
            Logger.getLogger(QuestionsAdd.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QuestionsAdd.class.getName()).log(Level.SEVERE, null, ex);
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
