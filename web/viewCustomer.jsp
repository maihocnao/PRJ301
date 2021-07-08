<%-- 
    Document   : viewCustomer
    Created on : Jun 11, 2021, 11:21:55 AM
    Author     : HP
--%>

<%@page import="entity.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% // get data from servlet (controller)
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            ArrayList<Customer> arr
                    = (ArrayList<Customer>) request.getAttribute("list");
            String title = request.getAttribute("tieude").toString();
        %>
        <table border="1">
            <caption><%=title%></caption>
            <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Username</th>
                    <th>status</th>
                    <th>update</th>
                    <th>delete</th>
                    <th>Change Password</th>

                </tr>
            </thead>
            <tbody>
                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                    <td><a href="ControllerCustomer?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                    <td><a href="ControllerCustomer?service=delete&id=<%=rs.getInt(1)%>">delete</a></td>
                    <td><a href="ControllerCustomer?service=changepass&id=<%=rs.getInt(1)%>">Change password</a></td>

                </tr>
                <%}%>
            </tbody>
        </table>
 <a href="/SE1507_MVC/addCustomer.jsp">Add new Customer</a>
       
           



    </body>
</html>
