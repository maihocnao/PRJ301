<%-- 
    Document   : viewAdmin
    Created on : Jun 16, 2021, 1:54:57 PM
    Author     : Viettech88.vn
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
         <%// get data from servlet (controller)
           ResultSet rs = (ResultSet)request.getAttribute("ketQua");
           //ep kieu sang result set
           ArrayList<Admin> arr = (ArrayList<Admin>)
                   request.getAttribute("list");
           String title = request.getAttribute("tieude").toString();
           
       %>
       <table border="1">
           <caption><%=title%></caption>
           <thead>
               <tr>
                   <th>ID</th>
                   <th>userName</th>
                   <%-- <th>password</th> --%>
                   <th>Delete</th>
                   <th>Update</th>
                   <th>Change password</th>
               </tr>
           </thead>
           <tbody>
               <% while(rs.next()) { %>
               <tr>
                   <td><%=rs.getInt(1)%></td>
                   <td><%=rs.getString(2)%></td>
                  <%-- <td type="password"><%=rs.getString(3)%></td>--%>
                    <td><a href="ControllerAdmin?service=delete&id=<%=rs.getInt(1)%>">delete</a></td>
                   <td><a href="ControllerAdmin?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                   <td><a href="ControllerAdmin?service=changepassword&id=<%=rs.getInt(1)%>">change password</a></td>
                   
               </tr>
               <%}%>
           </tbody>
           
       </table>
       <a href="/SE1507_MVC/addAdmin.jsp">Add new Admin</a>
       <br>
       <br>
       <a href="ControllerAdmin?service=login">Login</a>
       <iframe scr="https://www.facebook.com" title="FACEBOOK" height="800" width="800"></iframe>
    </body>
</html>
