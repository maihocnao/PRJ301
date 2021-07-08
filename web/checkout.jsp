<%-- 
    Document   : checkout.jsp
    Created on : Jun 25, 2021, 11:47:14 AM
    Author     : Viettech88.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Checkout!</h1>
<%
	//your business code come here
        //add data into database
	//kill session object
	session.invalidate();
%>

<h2><a href="ControllerProduct">New Shopping Cart</h2>
    </body>
</html>
