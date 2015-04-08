package sust.paperlessexm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sust.paperlessexm.bean.StudentBean;
import sust.paperlessexm.bean.TeachersBean;
import sust.paperlessexm.exception.GenericBusinessException;

/**
 *
 * @author Sm19
 */
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, GenericBusinessException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        // get login person information
        String usercode = request.getParameter("usercode");
        String password = request.getParameter("password");
        boolean valid = false;

        int id; // id=0 ->student id=1 ->teachers
        TeachersBean teachersBean;
        StudentBean studentBean;

        UserLogin userLoginSt = new UserLogin(usercode, password, 0);  // Check user valid.
        if (userLoginSt.login()) {
            ServletContext context = request.getServletContext();
            valid = true;
            id = 0;

            studentBean = userLoginSt.getStudentBean();

            HttpSession session = request.getSession(true); // a session for this user
            
            // -- set all attribute for next time use in this session time
            session.setAttribute("id", id);
            session.setAttribute("user", studentBean);
            session.setMaxInactiveInterval(30 * 60);

            Cookie userName = new Cookie("userName", usercode);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            
            // page dispatch
            response.sendRedirect("dashboard/index.jsp");
            //RequestDispatcher rd = request.getRequestDispatcher("/dashboard/index.jsp");
            //rd.forward(request, response);
        }

        if (!valid) {
            UserLogin userLoginTe = new UserLogin(usercode, password, 1);
            if (userLoginTe.login()) {
                valid = true;
                id = 1;
                teachersBean = userLoginTe.getTeachersBean();
                
                HttpSession session = request.getSession(true); // session create for this user
                
                // -- set all attribute for next time use in this session time
                session.setAttribute("id", id);
                session.setAttribute("user", teachersBean);
                session.setMaxInactiveInterval(30 * 60);
                
                // Cookie set for session control
                Cookie userName = new Cookie("userName", usercode);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                
                // Page Dispatch
                response.sendRedirect("dashboard/index.jsp");
                //RequestDispatcher rd = request.getRequestDispatcher("/dashboard/index.jsp");
                //rd.forward(request, response);
            }
        }

        if (!valid) {
            // for invalid user.. 
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");
            PrintWriter out = response.getWriter();
            out.println("<h1><center><font color=red>Either UserCode or password is wrong.</font></center></h1>");
            rd.include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (GenericBusinessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (GenericBusinessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
