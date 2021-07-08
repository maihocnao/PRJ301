<%-- 
    Document   : viewBill
    Created on : Jun 16, 2021, 1:54:06 PM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Bill"%>
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
        <%// get data from servlet (controller)
           ResultSet rs = (ResultSet)request.getAttribute("ketQua");
           //ep kieu sang result set
           ArrayList<Bill> arr = (ArrayList<Bill>)
                   request.getAttribute("list");
           //System.out.println(arr);
          // System.out.println("JSPPPPPPPPPPPPPPP");
           //System.out.println(arr);
           String title = request.getAttribute("tieude").toString();
           
       %>
       <table border="1">
           <thead>
               <tr>
                   <th>Bill ID</th>
                   <th>Date Create </th>
                   <th>C Name</th>
                   <th>C phone</th>
                   <th>C Address</th>
                   <th>Total</th>
                   <th>Status</th>
                   <th>Customer ID</th>
                  <%-- <th>Update</th>
                   <th>Delete</th>--%>
                   <th>View Bill Detail</th>
               </tr>
           </thead>
           <tbody>
               <%while(rs.next()){ %>
               <tr>
                   <td><%= rs.getString(1) %></td>
                   <td><%= rs.getString(2) %></td>
                   <td><%= rs.getString(3) %></td>
                   <td><%= rs.getString(4) %></td>
                   <td><%= rs.getString(5) %></td>
                   <td><%= rs.getDouble(6) %></td>
                    
                    <td>
                        <%= (rs.getInt(7)==1?"Enable":"Disable") %>
                    </td>
                    <td><%=rs.getInt(8) %></td>
                 <%--  <td><a href="ControllerBill?service=update&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">update</a></td>
                   <td><a href="ControllerBill?service=delete&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">delete</a></td>--%>
                   
                   <td><a href="ControllerBill?service=detail&oID=<%=rs.getString(1)%>">View Bill Detail</a></td>
                   
               </tr>
               <% }%>
           </tbody>
       </table>
    </body>
</html>
