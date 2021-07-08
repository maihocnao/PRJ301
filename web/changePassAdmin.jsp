<%-- 
    Document   : changePassAdmin
    Created on : Jun 20, 2021, 2:06:23 PM
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
           // String msg = request.getAttribute("msg").toString();
            String title =request.getAttribute("title").toString();
            String msg = "";
            try {
                msg=request.getAttribute("msg").toString();
            }
            catch (Exception e) {
                
            }
        %>
        <h3  style="color:Tomato;"><%= msg %></h3>
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
                    <input type="hidden" name="username" value="<%= admin.getUserName()%>" />
                        <td><%= admin.getUserName() %></td>
                    </tr>
                    <tr>
                        <td>Old Password</td>
                        <td>
                            <input type="password" name="oldpass" value="" />
                            <%  %>
                        </td>
                    </tr>
                    <tr>
                        <td>New Password</td>
                        <td>
                            <input type="password" name="newpass" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Re enter new pass word</td>
                        <td>
                            <input type="password" name="renewpass" value="">
                        </td>
                    </tr>
                    <tr>
                <input type="hidden" name="service" value="changed" />
                        <td><input type="submit" value="update" /></td>
                        <td><input type="reset" value="reset" /></td>
                    </tr>
                </tbody>
            </table>

            
        </form>
    </body>
</html>
