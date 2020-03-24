<%-- 
    Document   : teamDetail
    Created on : Feb 17, 2020, 12:44:39 AM
    Author     : raghul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Add Team Detail Here</h1>
         
         <c:if test="${not empty requestScope.errors}">
            <p>One or more issues found in submission. Fix it.</p>
            <ol>
                <c:forEach var="error" items="${requestScope.errors}" >
                    <li>There is an issue with ${error.propertyPath}. The message is ${error.message}</li>
                </c:forEach>
            </ol>
         </c:if>
        
        <form action="/rbalasubramanian1-fp/team" method="post">
            <div>
                <label for="teamName">Team Name: </label>
                <input value="${requestScope.td.teamName}" type="text" id="tName" name="teamName" required="required"/>
            </div>

            <div>
                <label for="captainName">Captain Name: </label>
                <input value="${requestScope.td.captainName}" type="text" id="cName" name="captainName"/>
            </div>

            <div>
                <label for="contact">Contact Number: </label>
                <input value="${requestScope.td.contact}" type="text" id="contactNumber" name="contact"/>
            </div> 
            
            <div>
                <label for="date">Date: </label>
                <input value="${requestScope.createdTime}" type="date" id="date" name="date"/>
            </div> 
            <button type="submit">Save team detail</button>
        </form>
    </body>
</html>
