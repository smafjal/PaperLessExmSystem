<%-- 
    Document   : logout
    Created on : Mar 10, 2015, 9:06:56 PM
    Author     : Sm19
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>logout</title>
    </head>
    <body>
        <%

            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("../index.html");
        %>
    </body>
</html>
