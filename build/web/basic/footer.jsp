<%-- 
    Document   : footer.jsp
    Created on : Mar 21, 2015, 5:12:46 AM
    Author     : Sm19
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
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
    </body>
</html>
