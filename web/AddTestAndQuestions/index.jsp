<%--
    Document   : AddTest
    Crn : Jan 21, 04:49 AM
    Author     : Sm19
--%>

<%@page import="sust.paperlessexm.bean.TestBean"%>
<%@page import="sust.paperlessexm.tool.DateTools"%>
<%@page import="antlr.Tool"%>
<%@page import="sust.paperlessexm.bean.TestSet"%>
<%@page import="sust.paperlessexm.api.impl.TestApiImpl"%>
<%@page import="sust.paperlessexm.api.impl.TeachesApiImpl"%>
<%@page import="sust.paperlessexm.api.impl.CourseApiImpl"%>
<%@page import="sust.paperlessexm.bean.CourseBean"%>
<%@page import="sust.paperlessexm.bean.TeachesBean"%>
<%@page import="java.util.Date"%>
<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>


<!DOCTYPE html>
<html>
    <head>
        <title>AddTestIndex</title>
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

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <form class="form-horizontal" action="../TestInfoVerification" method="POST">

                        <fieldset>

                            <!-- Form Name -->
                            <legend><center><h2>Test Information</h2></center></legend><br>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="testTitle">Test Title</label>  
                                <div class="col-md-5">
                                    <input id="testTitle" name="testTitle" value="Exam 01" placeholder="Exam 01" class="form-control input-md" required="" type="text">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="courseCode">Course Code</label>  
                                <div class="col-md-5">
                                    <input id="courseCode" name="courseCode" value="CSE9" placeholder="CSE9" class="form-control input-md" required="" type="text">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="courseSession">Course Session</label>  
                                <div class="col-md-5">
                                    <input id="courseSession" name="courseSession" value="2011" placeholder="2011" class="form-control input-md" required="" type="text">

                                </div>
                            </div>

                            <!-- Test date-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="testDate">Test Date</label>  
                                <div class="col-md-5">
                                    <input type="text" data-field="date" value="12-12-2015" palceholder="12-12-2012" readonly name="testDate" class="form-control input-md">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="testTime">Test Time</label>  
                                <div class="col-md-5">
                                    <input type="text" data-field="time" value="12:23" palceholder="12:23" readonly name="testTime" class="form-control input-md">
                                </div>
                            </div>
                            <div id="dtBox"></div> 

                            <!-- Text input-->
                            <label class="col-md-4 control-label" for="testLength">Length:</label>  
                            <div class="form-group">
                                <label class="col-md-1 control-label" for="testLength">Hour:</label>  
                                <div class="col-md-1 pull-left">
                                    <input id="testLength" name="testHour" value="2" placeholder="2" class="form-control input-md" required="" type="text">
                                </div>
                                <label class="col-md-1 control-label" for="testLength">Min:</label>  
                                <div class="col-md-2">
                                    <input id="testLength" name="testMin" value="2" placeholder="2" class="form-control input-md" required="" type="text">

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="testMarks">Test Marks</label>  
                                <div class="col-md-5">
                                    <input id="testMarks" name="testMarks" value="100" placeholder="" class="form-control input-md" required="" type="text">

                                </div>
                            </div>

                            <!-- Textarea -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="testDescription">Test Description</label>
                                <div class="col-md-4">                     
                                    <textarea class="form-control" id="testDescription" value="A test Info entered" name="testDescription" style="width:460px;height:100px;"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <!-- Button -->
                                    <br><br>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="submit"></label>
                                        <div class="col-md-4">
                                            <button name="submit" class="btn btn-danger:hover" style="width:455px;height:40px;color:black;background-color: gray;font-weight: bolder;">
                                                Create Test</button>
                                        </div>
                                    </div>
                                </div> <!-- col-md-6-->
                            </div>
                        </fieldset>
                    </form>
                </div> <!-- col-md-6-->
                <br><br>

            </div>

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
                }

            </script>
    </body>
</html>
