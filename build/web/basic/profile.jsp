<%-- 
    Document   : profile
    Created on : Jan 16, 2015, 5:20:50 AM
    Author     : Sm19
--%>

<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile View</title>
    </head>
    <body>
        
        <%
            Integer id = -1;
            if (session.getAttribute("id") == null) {
                response.sendRedirect("index.html");
            } else {
                id = (Integer) session.getAttribute("id");
            }
            request.getRequestDispatcher("/header.jsp").include(request, response);
            if (id == 1) {
                TeachersBean teachersBean = (TeachersBean) session.getAttribute("user");
                String userInfo;
                userInfo="Name: "+teachersBean.getTeachersName()+"<br>";
                userInfo=userInfo+"Email: "+teachersBean.getEmailAddress()+"<br>";
                userInfo=userInfo+"Contact No: "+teachersBean.getContactNo()+"<br>";
                userInfo=userInfo+"Dept: "+teachersBean.getTeachersDept();
                out.println("<h1> Hello...!!<br>"+userInfo+"</h1>");
                
            } else {
                StudentBean studentBean = (StudentBean) session.getAttribute("user");
                String userInfo;
                userInfo="Name: "+studentBean.getStudentName()+"<br>";
                userInfo=userInfo+"Email: "+studentBean.getEmailAddress()+"<br>";
                userInfo=userInfo+"Contact No: "+studentBean.getContactNo()+"<br>";
                
                out.println("<h1> Hello...!!<br>"+userInfo+"</h1>");
            }
        %>
        
    </body>
</html>
