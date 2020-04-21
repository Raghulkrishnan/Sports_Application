<%-- 
    Document   : confirmation
    Created on : Feb 17, 2020, 1:16:22 AM
    Author     : raghu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
        <h1>User input has passed the validations!</h1>
        <p>Team created successfully! Team Details below: </p>
        <ul>
            <li>Team Name : ${requestScope.td.teamName}</li>
            <li>Captain Name : ${requestScope.td.captainName}</li>
            <li>Contact : ${requestScope.td.contact}</li>
            <li>Level : ${requestScope.td.level}</li>
            
        </ul>
    </body>
</html>
