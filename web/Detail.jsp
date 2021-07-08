<%-- 
    Document   : Detail
    Created on : Jun 21, 2021, 1:32:41 PM
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
        <% 
            String username = null;
            String password = null;
            
           try {
                 username = request.getAttribute("user").toString();
                 password = request.getAttribute("pass").toString();
           }
           
           catch(Exception e) {
               
           }
           
            
        if (username==null) {
                      RequestDispatcher dis=
                        request.getRequestDispatcher("/Login.jsp");
                // loclalhost:8080/webroot/filename
                // run
                dis.forward(request, response);

          %>
          
        <%}
        else {
        %>
        <h1>Hello: <%= username %></h1>
        <h3>Display Menu to manager</h3>
        <table border="0">
            
            <tbody>
                <tr>
                    <td>Category</td>
                    <td><a href="CategoryController">link</a></td>
                </tr>
                <tr>
                    <td>Product</td>
                    <td><a href="ProductControllerro">link</a></td>
                </tr>
                <tr>
                    <td>Customer</td>
                    <td><a href="CustomerController">link</a></td>
                </tr>
                <tr>
                    <td>Bill</td>
                    <td><a href="BillController">link</a></td>
                </tr>
            </tbody>
        </table>
<%} %>
    </body>
</html>
