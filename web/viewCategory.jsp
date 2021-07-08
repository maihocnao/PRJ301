<%-- 
    Document   : viewCategory
    Created on : Jun 14, 2021, 2:06:24 AM
    Author     : Viettech88.vn
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Category"%>
<%@page import="java.sql.ResultSet"%>
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
           ArrayList<Category> arr = (ArrayList<Category>)
                   request.getAttribute("list");
           String title = request.getAttribute("tieude").toString();
           
       %>
       <table border="1">
           <caption><%=title%></caption>
           <thead>
               <tr>
                   <th>ID</th>
                   <th>Name</th>
                   <th>Status</th>
                   <th>Delete</th>
                   <th>Update</th>
               </tr>
           </thead>
           <tbody>
               <% while(rs.next()) { %>
               <tr>
                   <td><%=rs.getInt(1)%></td>
                   <td><%=rs.getString(2)%></td>
                   <td><%=(rs.getInt(3)==1?"Enable":"Disable")%></td>
                    <td><a href="ControllerCategory?service=delete&id=<%=rs.getInt(1)%>">delete</a></td>
                   <td><a href="ControllerCategory?service=update&id=<%=rs.getInt(1)%>">update</a></td>
                   
               </tr>
               <%}%>
           </tbody>
       </table>
 <a href="/SE1507_MVC/addCategory.jsp">Add new Category</a>
       
    </body>
</html>
