<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sust.paperlessexm.api.impl.TeachersApiImpl"%>
<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teachers Information</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/DateTimePicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/DateTimePicker.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dataTablecss.css" />
    </head>
    <body>
        <%

            int id;
            String v = request.getContextPath();
            String addTestStr = "<li><a href='" + v + "/AddTestAndQuestions/index.jsp'>Add Test</a></li>";
            StudentBean loginStudent = new StudentBean();
            TeachersBean loginTeacher = new TeachersBean();

            id = (Integer) session.getAttribute("id");
            if (id == 1) {
                loginTeacher = (TeachersBean) session.getAttribute("user");
            } else {
                loginStudent = (StudentBean) session.getAttribute("user");
            }
        %>

        <!--  nav var-->
        <nav class="navbar navbar-inverse" id="navMyId">
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/dashboard/index.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/SearchWeb/index.jsp">Search</a></li>
                        <%
                            if (id == 1) {
                                out.println(addTestStr);
                            }
                        %>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%
                        String navUserName;
                        if (id == 1) {
                            navUserName = loginTeacher.getTeachersName();
                        } else {
                            navUserName = loginStudent.getStudentName();
                        }
                        out.println("<li ><a href='#'>" + navUserName + "</a></li>");
                    %>
                    <li ><a href="${pageContext.request.contextPath}/basic/logout.jsp">Logout</a></li>
                </ul>
            </div>
        </nav>

        <%
            String regname = request.getParameter("regname");
            String name = request.getParameter("name");
            String dept = request.getParameter("dept");

            TeachersApiImpl teachersApiImpl = new TeachersApiImpl();
            List<TeachersBean> output = new ArrayList();

            if (!regname.isEmpty()) {
                output = teachersApiImpl.findTeachersByEmployeeCode(regname);
            } else if (!name.isEmpty()) {
                output = teachersApiImpl.findTeachersByTeachersName(name);

            } else if (!dept.isEmpty()) {
                output = teachersApiImpl.findTeachersByTeachersDept(dept);
            }
        %>

        <div class="container">
            <div class="row">
                <legend>Teachers Information</legend>
                <div class="col-md-12">

                    <%
                        if (output.isEmpty()) {
                            String tmpStr = "<h3 style=\"color:red;\">" + "There is no one that you want!</h3>";
                            out.println(tmpStr);
                        } else {
                            for (int i = 0; i < output.size(); i++) {
                                String str = "<h3>";
                                str += "Registration no: " + output.get(i).getEmployeeCode() + "<br>";
                                str += "Name: " + output.get(i).getTeachersName() + "<br>";
                                str += "Email: " + output.get(i).getEmailAddress() + "<br>";
                                str += "Contact Number: " + output.get(i).getContactNo() + "<br>";
                                str += "Department: " + output.get(i).getTeachersDept() + "<br>";
                                str += "</h3><br>";
                                out.println(str);
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
