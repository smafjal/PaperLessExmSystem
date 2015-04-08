<%-- 
    Document   : testResult
    Created on : Mar 18, 2015, 12:27:45 AM
    Author     : Sm19
--%>

<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sust.paperlessexm.bean.QuestionsBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test Result</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            int totalMarks = (Integer) session.getAttribute("totalMarks");
            Hashtable notEvalute = (Hashtable) session.getAttribute("notEvalute");
        %>

        <div class="container">
            <div class="row">
                <legend>Your Result</legend>
                <div class="col-md-12">
                    <h3> Marks: <%=totalMarks%></h3>
                    <h3> There <%=notEvalute.size()%> Questions are not examine!</h3><br>

                    <%
                        Enumeration questionTitle = notEvalute.keys();
                        while (questionTitle.hasMoreElements()) {
                            String title = (String) questionTitle.nextElement();
                            String userAns = (String) notEvalute.get(title);
                            if (userAns == "undefined") {
                                userAns = "";
                            }
                            String str = "<p>Questions:" + title + "</p1><br>";
                            str += "<p>User Ans: " + userAns + "</p><br><br>";
                            out.println(str);
                        }
                    %>
                </div>
            </div>

        </div>
        <br><br><br>

        <!-- footer -->
    <legend></legend>
    <div class="container">
        <div class="row">
            <div class="col-md-2 col-centered"><h4>Current Time:</h4></div>
            <div class="col-md-10 col-centered" id="clockbox"></div><br><br><br><br>
        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(function()
        {
            $("#dtBox").DateTimePicker({
                dateFormat: "MM-dd-yyyy",
                timeFormat: "HH:mm",
                dateTimeFormat: "MM-dd-yyyy HH:mm:ss AA"

            });
        });

        tday = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        tmonth = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        function GetClock() {
            var d = new Date();
            var nday = d.getDay(), nmonth = d.getMonth(), ndate = d.getDate(), nyear = d.getYear();
            var nhour = d.getHours(), nmin = d.getMinutes(), nsec = d.getSeconds(), ap;

            if (nhour == 0) {
                ap = " AM";
                nhour = 12;
            }
            else if (nhour < 12) {
                ap = " AM";
            }
            else if (nhour == 12) {
                ap = " PM";
            }
            else if (nhour > 12) {
                ap = " PM";
                nhour -= 12;
            }

            if (nyear < 1000)
                nyear += 1900;
            if (nmin <= 9)
                nmin = "0" + nmin;
            if (nsec <= 9)
                nsec = "0" + nsec;

            document.getElementById('clockbox').innerHTML = "<h4>" + tday[nday] + ", " + tmonth[nmonth] + " " + ndate + ", " + nyear + " " + nhour + ":" + nmin + ":" + nsec + ap + "</h4>";
        }

        window.onload = function() {
            GetClock();
            setInterval(GetClock, 1000);
        };
    </script>
</body>
</html>
