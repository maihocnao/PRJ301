<%-- 
    Document   : viewBillDetail
    Created on : Jun 16, 2021, 1:54:15 PM
    Author     : Viettech88.vn
--%>

<%@page import="entity.BillDetail"%>
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
           ArrayList<BillDetail> arr = (ArrayList<BillDetail>)
                   request.getAttribute("list");
          // System.out.println("JSPPPPPPPPPPPPPPP");
           //System.out.println(arr);
           String title = request.getAttribute("tieude").toString();
           
       %>
       <table border="1">
           <thead>
               <tr>
                   <th>Product ID</th>
                   <th>Bill ID</th>
                   <th>Quantity</th>
                   <th>Price</th>
                   <th>Total</th>
                   <th>Update</th>
                   <th>Delete</th>
               </tr>
           </thead>
           <tbody>
               <%while(rs.next()){ %>
               <tr>
                   <td><%= rs.getString(1) %></td>
                   <td><%= rs.getString(2) %></td>
                   <td><%= rs.getInt(3) %></td>
                   <td><%= rs.getDouble(4) %></td>
                   <td><%= rs.getDouble(5) %></td>
                   <td><a href="ControllerBillDetail?service=update&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">update</a></td>
                   <td><a href="ControllerBillDetail?service=delete&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">delete</a></td>
               </tr>
               <% }%>
           </tbody>
       </table>

       
    </body>
</html>
