/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sust.paperlessexm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sust.paperlessexm.api.OptionsStoresApi;
import sust.paperlessexm.api.impl.OptionStoresApiImpl;
import sust.paperlessexm.api.impl.QuestionsApiImpl;
import sust.paperlessexm.bean.QuestionsBean;
import sust.paperlessexm.entity.OptionsStores;
import sust.paperlessexm.exception.GenericBusinessException;
import sust.paperlessexm.tool.PairIntStr;

/**
 *
 * @author Sm19
 */
public class ExamEvalute extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, GenericBusinessException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        String data = request.getParameter("data");

        int testId = (int) session.getAttribute("testId");
        QuestionsApiImpl questionsApiImpl = new QuestionsApiImpl();
        List<QuestionsBean> questions = questionsApiImpl.findQuestionsByTestId(testId);
        
        Hashtable hashTable = new Hashtable();
        StringTokenizer tk = new StringTokenizer(data, "%");
        while (tk.hasMoreTokens()) {
            StringTokenizer tk2 = new StringTokenizer(tk.nextToken(), "@");
            int qNumber = Integer.parseInt(tk2.nextToken());
            String ans = tk2.nextToken();
            hashTable.put(qNumber,ans);
        }
        
        ArrayList<QuestionsBean> notEvalute = new ArrayList<>();
        int totalMarks = 0;

        Hashtable notEva = new Hashtable();
        for (int i = 0; i < questions.size(); i++) {
            
            int qNo = questions.get(i).getQuestionNo();
            String ans = (String) hashTable.get(qNo);
            String correctAns = (String)questions.get(i).getCorrectAns();

            if (questions.get(i).getIsDirectMarking()) {
                if (correctAns.equals(ans)) {
                    totalMarks += questions.get(i).getTotalMarks();
                }
            } else {
                //notEvalute.add(questions.get(i));
                OptionStoresApiImpl optionStoresApiImpl = new OptionStoresApiImpl();
                String title = optionStoresApiImpl.findOptionsStoresByQuestionsId(questions.get(i).getQuestionsId()).get(0).getOptionsText().substring(6);
                notEva.put(title, ans);
            }
        }
        session.setAttribute("totalMarks", totalMarks);
        session.setAttribute("notEvalute", notEva);
        RequestDispatcher rd = request.getRequestDispatcher("/exam/testResult.jsp");
        rd.forward(request, response);
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
            Logger.getLogger(ExamEvalute.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExamEvalute.class.getName()).log(Level.SEVERE, null, ex);
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
