<%-- 
    Document   : updateAdmin
    Created on : Jun 16, 2021, 1:55:10 PM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            Admin admin = (Admin)request.getAttribute("admin");
            
            String title =request.getAttribute("title").toString();
        %>
        <form action="ControllerAdmin" method="POST">
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>ID</td>
                <input type="hidden" name="adminID" value="<%= admin.getAdminID()%>" />
                            
                       <%-- <td type="text" name="cateID" value="<%=cate.getCateID()%>"><%=cate.getCateID()%> </td>--%>
                        <td><%= admin.getAdminID() %></td>
                    </tr>
                    <tr>
                        <td>userName</td>
                        <td><input type="text" name="username" value="<%= admin.getUserName() %>" /></td>
                    </tr>
                   <%-- <tr>
                        <td>Password</td>
                        <td>
                            <input type="text" name="password" value="<%= admin.getPassowrd() %>" />
                        </td>
                    </tr> --%>
                    <tr>
                <input type="hidden" name="service" value="updated" />
                        <td><input type="submit" value="update" /></td>
                        <td><input type="reset" value="reset" /></td>
                    </tr>
                </tbody>
            </table>

            
        </form>
    </body>
</html>
