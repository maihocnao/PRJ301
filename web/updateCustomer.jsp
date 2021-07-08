<%-- 
    Document   : updateCustomer
    Created on : Jun 16, 2021, 1:54:26 PM
    Author     : Viettech88.vn
--%>
<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
    </head>
    <body>
        <%
            Customer cus= (Customer)request.getAttribute("cus");
            String title =request.getAttribute("formtitle").toString();
        %>
        <h3>Update Customer</h3>
        <form action="ControllerCustomer" method="POST">
            
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>ID</td>
                <input type="hidden" name="cid" value="<%= cus.getCid()%>" />
                     <td><%= cus.getCid() %></td>
                        <%--<td name="cid" value="<%=cus.getCid()%>"> <%=cus.getCid() %> </td>--%>
                    </tr>
                    <tr>
                        <td>Name</td>
                        
                        <td><input type="text" name="cname" value="<%= cus.getCname()%>"> </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" name="cphone" value="<%=cus.getCphone() %>"> </td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="caddress" value="<%= cus.getcAddress() %>"></td>
                    </tr>
                    <tr>
                        <td>username</td>
                        <td><input type="text" name="username" value="<%= cus.getUsername() %>"></td>
                    </tr>
                   /<%-- <tr>
                        <td>password</td>
                        <td><input  type="password" name="password" value="<%= cus.getPassword() %>"</td>
                    </tr>--%>
                    <tr>
                        <td>Status</td>
                       
                        <td>
                            <input type="radio" name="status" value ="1" <%=(cus.getStatus()==1?"checked":"") %> > Enable
                            <input type="radio" name="status" value ="0" <%=(cus.getStatus()==0?"checked":"") %> > Disable
                           
                        </td>
                    </tr>
                     
                    <input type="hidden" name="service" value="updated">
                    <td><input type="submit" value="submit"></td>
                    <td><input type="reset" value="reset"></td>
                    </tr>
                </tbody>
            </table>


        </form>
    </body>
</html>
