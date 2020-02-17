<%-- 
    Document   : confirmation
    Created on : Feb 17, 2020, 1:16:22 AM
    Author     : raghu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
        <h1>User input has passed the validations!</h1>
        
        <ul>
            <li>${requestScope.td.teamName}</li>
            <li>${requestScope.td.captainName}</li>
            <li>${requestScope.td.contact}</li>
            <li>${requestScope.td.level}</li>
            <li>${requestScope.td.createdTime}</li>
            
        </ul>
    </body>
</html>
