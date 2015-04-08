<%-- 
    Document   : addQuestions
    Created on : Mar 12, 2015, 2:16:01 AM
    Author     : Sm19
--%>

<%@page import="sust.paperlessexm.bean.TeachersBean"%>
<%@page import="sust.paperlessexm.bean.StudentBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>addQuestions</title>
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
                        <%                            if (id == 1) {
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
                <legend>Add Questions</legend>
                <div class="col-md-12">
                    <p>Question Type:</p>
                    <div>
                        <select id="form_type">
                            <option>Options</option>
                            <option value="text">Text</option>
                            <option value="mcq3">MCQ3</option>
                            <option value="mcq4">MCQ4</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div id="form_div"></div>
            </div>
        </div>


        <script>

            $("#form_type").change(function() {
                var ques_type = $(this).val();
                var ele = "";

                if (ques_type == "text") {
                    // make text titile
                    ele = '<div class="form-group ">' +
                            '<br><br>' +
                            '<legend>Text Type Question</legend>' +
                            '<h4 id="msg" style="color:red;"></h4>' +
                            '<label class="control-label" for="Question title">Question Title:</label>' +
                            '<input id="text-title" class="form-control" name="title" type="text" value="" placeholder="Question title">' +
                            '<div class="form-group">' +
                            '<label for="title" id="" class="">Total Marks</label>' +
                            '<input id="totalMarks" class="radio-option form-control" name="title" type="number" value="" placeholder="total marks">' +
                            '</div>' +
                            '<button id="text-submit" class="btn btn-primary">Create</button>' +
                            '</div>';
                } else if (ques_type == 'mcq3') {

                    ele =
                            '<div class="form-group ">' +
                            '<br><br>' +
                            '<legend>MCQ-3 Type Question</legend>' +
                            '<h4 id="msg" style="color:red;"></h4>' +
                            '<label for="title" id="" class="">Question Title</label>' +
                            '<input id="mcq3-title" class="form-control" name="title" type="text" value="" placeholder="Question title">' +
                            '<div id="radio_options_div"></div>' +
                            '</div>';

                    ele += correct_answer();

                    ele += add_new_option() + add_new_option() + add_new_option() + add_TotalMarks() + '<button id="mcq3-submit" class="btn btn-primary">Create</button>';

                } else if (ques_type == 'mcq4') {
                    ele =
                            '<div class="form-group ">' +
                            '<br><br>' +
                            '<legend>MCQ-4 Type Question</legend>' +
                            '<h4 id="msg" style="color:red;"></h4>' +
                            '<label for="title" id="" class="">Question Title</label>' +
                            '<input id="mcq4-title" class="form-control" name="title" type="text" value="" placeholder="Question title">' +
                            '<div id="radio_options_div"></div>' +
                            '</div>';

                    ele += correct_answer();

                    ele += add_new_option() + add_new_option() + add_new_option() + add_new_option() + add_TotalMarks() + '<button id="mcq4-submit" class="btn btn-primary">Create</button>';
                }


                $("#form_div").empty().append($(ele));
            });

            function add_new_option() {
                // make radio option
                var ele =
                        '<div class="form-group">' +
                        '<label for="title" id="" class="">Option Title</label>' +
                        '<input id="" class="radio-option form-control" name="title" type="text" value="" placeholder="">' +
                        '</div>';

                return ele;
            }
            ;

            function correct_answer() {
                // make radio option
                var ele =
                        '<div class="form-group">' +
                        '<label for="title" id="" class="">Correct answer</label>' +
                        '<input id="correctAnswer" class="radio-option form-control" name="title" type="text" value="" placeholder="correct Answer">' +
                        '</div>';

                return ele;
            }
            ;

            function add_TotalMarks() {
                var ele = '<div class="form-group">' +
                        '<label for="title" id="" class="">Total Marks</label>' +
                        '<input id="totalMarks" class="radio-option form-control" name="title" type="number" value="" placeholder="total marks">' +
                        '</div>';
                return ele;
            }


            $("body").on('click', '.delete_option', function(e) {
                $(this).parent().remove();
            });


            $("body").on('click', '#text-submit', function() {

                $("#msg").text(""); // clear error msg

                var title = $("#text-title").val().trim();

                if (title == "") {
                    $("#msg").text("Title is empty");
                    return;
                }

                // var data = 'type=text&title=' + title;


                var option = $("#totalMarks").val().trim();

                /*
                 $(".radio-option").each(function(ind, val) {
                 
                 var opt_title = $(val).val().trim();
                 
                 if (opt_title != "") {
                 options.push($(val).val());
                 }
                 });
                 */

                var data = 'type=text&title=' + title + '&options=' + option;
                //document.getElementById("form_div").innerHTML=data;

                $.ajax({
                    url: 'http://localhost:8084/PaperLessExmSystem/QuestionsAdd',
                    data: data,
                    success: function(msg) {
                        $("#msg").text(msg);
                    },
                    error: function(msg) {
                        $("#msg").text("Some Error!");
                    }
                });
            });

            $("body").on('click', '#mcq3-submit', function() {

                $("#msg").text(""); // clear error msg

                var title = $("#mcq3-title").val().trim();
                var options = [];

                $(".radio-option").each(function(ind, val) {

                    var opt_title = $(val).val().trim();

                    if (opt_title != "") {
                        options.push($(val).val());
                    }
                });

                if (title == "") {
                    $("#msg").text("Title is empty");
                    return;
                } else if (options.length == 0) {
                    $("#msg").text("There is no option");
                    return;
                }


                var data = 'type=text&title=' + title + '&options=' + options.join(',');

                $.ajax({
                    url: 'http://localhost:8084/PaperLessExmSystem/QuestionsAdd',
                    data: data,
                    success: function(msg) {
                        $("#msg").text(msg);
                    },
                    error: function(msg) {
                        $("#msg").text(msg);
                    }
                });
            });

            $("body").on('click', '#mcq4-submit', function() {

                $("#msg").text(""); // clear error msg

                var title = $("#mcq4-title").val().trim();
                var options = [];

                $(".radio-option").each(function(ind, val) {

                    var opt_title = $(val).val().trim();

                    if (opt_title != "") {
                        options.push($(val).val());
                    }
                });

                if (title == "") {
                    $("#msg").text("Title is empty");
                    return;
                } else if (options.length == 0) {
                    $("#msg").text("There is no option");
                    return;
                }


                var data = 'type=text&title=' + title + '&options=' + options.join(',');


                $.ajax({
                    url: 'http://localhost:8084/PaperLessExmSystem/QuestionsAdd',
                    data: data,
                    success: function(msg) {
                        $("#msg").text(msg);
                    },
                    error: function() {
                        $("#msg").text("Error to submit your request");
                    }
                });

                console.log(data);
            });

        </script>

    </body>
</html>
