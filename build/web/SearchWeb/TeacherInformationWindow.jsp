<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher Information</title>
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
        <div class="container">
            <div class="row">
                <legend><center>You need to fill minimum one field</center></legend><br>
                <div class="col-md-12">

                    <form role="form" class="form-horizontal" action="TeacherInformation.jsp" method="POST">

                        <div class="form-group">
                            <label class="control-label col-md-2" for="Employee Code">Employee Code:</label>
                            <div class="col-md-8">
                                <input name="regname" type="text" class="form-control" id="employeeCode" placeholder="Employee Code">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-2" for="Name">Name:</label>
                            <div class="col-md-8"> 
                                <input  name="name" type="text" class="form-control" id="Name" placeholder="Name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-2" for="Department">Department:</label>
                            <div class="col-md-8"> 
                                <input  name="dept" type="text" class="form-control" id="dept" placeholder="Department">
                            </div>
                        </div>

                        <br>
                        <div class="form-group"> 
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Submit</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
