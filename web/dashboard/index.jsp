<%-- 
    Document   : dashboardIndex
    Created on : Mar 4, 2015, 12:37:41 AM
    Author     : Sm19
--%>

<%@page import="java.util.Date"%>
<%@page import="sust.paperlessexm.tool.DateCheck"%>
<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.entity.Teachers"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sust.paperlessexm.bean.TestBean"%>
<%@page import="sust.paperlessexm.api.impl.TestApiImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/DateTimePicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/DateTimePicker.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dataTablecss.css" />
        <title>Dashboard Index</title>
    </head>

    <body>
        <%!
            // Variable Declaration
            String td = "<td>", tde = "</td>", tr = "<tr>", tre = "</tr>", ae = "</a>";
            TestApiImpl testApiImpl = new TestApiImpl();

        %>


        <!-- ---------- nav var formation and info collect -->
        <%
            int id, hitTimer;
            String v = request.getContextPath();
            String addTestStr = "<li><a href='" + v + "/AddTestAndQuestions/index.jsp'>Add Test</a></li>";
            String aHref = "<a href='" + v + "/TestEntryVerification?testId=";
            StudentBean loginStudent = new StudentBean();
            TeachersBean loginTeacher = new TeachersBean();

            id = (Integer) session.getAttribute("id");

            if (id == 1) {
                loginTeacher = (TeachersBean) session.getAttribute("user");
                hitTimer = 0;
                session.setAttribute("hitTimer", hitTimer);
            } else {
                loginStudent = (StudentBean) session.getAttribute("user");
            }
        %>
        <!--  nav var start-->
        <nav class="navbar navbar-inverse" id="navMyId">
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="#">Home</a></li>
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
        <!-- nav var complete-->



        <table id="myTable" class="row-border" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Course</th>
                    <th>Start Time</th>
                    <th>Length</th>
                    <th>Manager</th>
                    <th>Type</th>
                </tr>
            </thead>

            <tbody>
                <%
                    List<TestBean> testList = testApiImpl.getTestList();
                    for (int i = 0; i < testList.size(); i++) {

                        String tId = testList.get(i).getTestId() + "\'>";
                        String testRow = tr;
                        testRow = testRow + td + testList.get(i).getTestId() + tde;
                        testRow = testRow + td + aHref + tId + testList.get(i).getTestTitle() + ae + tde;
                        testRow = testRow + td + testList.get(i).getCourseId().getCourseCode() + tde;
                        testRow = testRow + td + testList.get(i).getTestTime() + tde;
                        testRow = testRow + td + testList.get(i).getTestDuration()+"min" + tde;
                        testRow = testRow + td + testList.get(i).getTeachesId().getTeachersId().getTeachersName() + tde;

                        String type = DateCheck.dateStatus(new Date(), testList.get(i).getTestTime(), testList.get(i).getTestDuration());
                        testRow = testRow + td + type + tde + tre;
                        out.println(testRow);
                    }
                %>
            </tbody>
        </table>

        <br><br><br>
        <!-- footer -->
    <legend></legend>
    <div class="container">
        <div class="row">
            <div class="col-md-2 col-centered"><h4>Current Time:</h4></div>
            <div class="col-md-10 col-centered" id="clockbox"></div><br><br><br><br>
        </div>
    </div>



    <script>
        $(document).ready(function() {
            $('#myTable').DataTable();
        });
    </script>

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
