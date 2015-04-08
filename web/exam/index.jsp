<%-- 
    Document   : index.jsp
    Created on : Mar 10, 2015, 9:45:08 PM
    Author     : Sm19
--%>

<%@page import="java.util.Date"%>
<%@page import="sust.paperlessexm.tool.DateCheck"%>
<%@page import="sust.paperlessexm.bean.TestBean"%>
<%@page import="sust.paperlessexm.api.impl.TestApiImpl"%>
<%@page import="sust.paperlessexm.api.impl.TestRegistrationApiImpl"%>
<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="sust.paperlessexm.api.impl.QuestionsTypeApiImpl"%>
<%@page import="sust.paperlessexm.bean.OptionsStoresBean"%>
<%@page import="sust.paperlessexm.api.impl.OptionStoresApiImpl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sust.paperlessexm.bean.QuestionsBean"%>
<%@page import="sust.paperlessexm.api.impl.QuestionsApiImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>index</title>
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
                    <li ><a href="basic/logout.jsp">Logout</a></li>
                </ul>
            </div>
        </nav>

        <%
            int testId = Integer.parseInt((String) request.getParameter("testId"));
            QuestionsApiImpl questionsApiImpl = new QuestionsApiImpl();
            List<QuestionsBean> questions = questionsApiImpl.findQuestionsByTestId(testId);
            Collections.sort(questions, new Comparator<QuestionsBean>() {
                public int compare(QuestionsBean A, QuestionsBean B) {
                    return A.getQuestionNo() - B.getQuestionNo();
                }
            });
            String divCls = "<div class='type_radio'>";
            String divQS = "<div class='question'>";
            String divQE = "</div>";

            String inputOp = "<input class='answer' type=\"radio\" name='answer";
        %>

        <div class="container">
            <div class="row">

                <div class="col-md-12">
                    <legend>All Question</legend>

                    <%
                        OptionStoresApiImpl optionStoresApiImpl = new OptionStoresApiImpl();
                        QuestionsTypeApiImpl questionsTypeApiImpl = new QuestionsTypeApiImpl();

                        for (int i = 0; i < questions.size(); i++) {
                            QuestionsBean newQuestion = questions.get(i);
                            List<OptionsStoresBean> optionsStoresBeans = optionStoresApiImpl.findOptionsStoresByQuestionsId(newQuestion.getQuestionsId());
                            int qNo = newQuestion.getQuestionNo();

                            String qStr;

                            if (newQuestion.getQuestionsTypeId().getNoOfOptions() == 0) {
                                qStr = "<div class='type_text'> <div class='question' ";
                                qStr += "data-qid='" + qNo + "'><h3>";
                                qStr += qNo + ": " + optionsStoresBeans.get(0).getOptionsText().substring(6) + "?</h3></div>";
                                qStr += "<input type=\"text\" name='answer" + qNo + "'";
                                qStr += "value=''/>";
                            } else {
                                qStr = "<div class='type_radio'> <div class='question'";
                                qStr += "data-qid='" + qNo + "'><h3>";
                                for (int j = 0; j < optionsStoresBeans.size(); j++) {
                                    if (j == 0) {
                                        qStr += qNo + ": " + optionsStoresBeans.get(j).getOptionsText().substring(6) + "?</h3></div>";
                                    } else {
                                        qStr += "<input class='answer' type=\"radio\" name='answer" + qNo + "'";
                                        qStr += "value='" + optionsStoresBeans.get(j).getOptionsText() + "'/>";
                                        qStr += optionsStoresBeans.get(j).getOptionsText() + "<br>";
                                    }
                                }
                            }
                            qStr += "</div><br>";
                            out.println(qStr);
                        }
                    %>
                </div>
            </div>
            <div class="row">
                <br><br>
                <form name="hidden form" action="ExamEvalute" method="POST">
                    <input id="data" type="hidden" name="data" value="data">
                    <button id='btn'>Submit Your Answer</button>
                </form>
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
        var data = "";

        $('#btn').click(function() {

            $('.type_radio').each(function() {
                var q = $('.question', this).text();
                var ans = $('input[name^=answer]:checked', this).val();
                var qid = $('.question', this).attr('data-qid');
                data += qid + "@" + ans + "%";

                //console.log(q, ans, qid);
            });

            $('.type_text').each(function() {
                var q = $('.question', this).text();
                var ans = $('input[name^=answer]', this).val();
                var qid = $('.question', this).attr('data-qid');
                if (ans == "") {
                    ans = 'undefined';
                }
                data += qid + "@" + ans + "%";
                //console.log(q, ans);
            });
            //console.log(data);
            var sendData = 'type=text&data=' + data;
            $("#data").val(data);

            /*
             $.ajax({
             url: 'ExamEvalute',
             data: sendData,
             success: function(msg) {
             $("#msg").text(msg);
             },
             error: function() {
             $("#msg").text("Error to submit your request");
             }
             });
             */
            console.log(data);
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

