<%-- 
    Document   : viewDetail
    Created on : Jun 21, 2021, 12:33:14 PM
    Author     : Viettech88.vn
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.BillDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%   ResultSet rs = (ResultSet)request.getAttribute("ketQua");
             ArrayList<BillDetail> arr = (ArrayList<BillDetail>)
                   request.getAttribute("list");
             Double total = (Double)request.getAttribute("total");
          // System.out.println("JSPPPPPPPPPPPPPPP");
           //System.out.println(arr);
           String title = request.getAttribute("tieude").toString();
           String tenKH =request.getAttribute("cname").toString();
        %>
        <h3>
           <%= tenKH %>
        </h3>
        <table border="1">
           <thead>
               <tr>
                   <th>Product ID</th>
                   <th>Bill ID</th>
                  
                   <th>Quantity</th>
                   <th>Price</th>
                   <th>Total</th>
                   <%--<th>Update</th>
                   <th>Delete</th>--%>
               </tr>
           </thead>
           <tbody>
               <%if (arr.size()>=1) {
                   for(BillDetail bd: arr) { %>
               <tr>
                   <td><%= bd.getPid() %></td>
                   <td><%= bd.getoID() %></td>
                   
                   <td><%= bd.getQuantity() %></td>
                   <td><%= bd.getMoney() %></td>
                   <td><%= bd.getTotal() %></td>
                  <%-- <td><a href="ControllerBillDetail?service=update&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">update</a></td>
                   <td><a href="ControllerBillDetail?service=delete&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">delete</a></td>--%>
               </tr>
               <%} }%>
               <tr>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td></td>
                   <td><%= total %></td>
               </tr>
           </tbody>
       </table>
       
    </body>
</html>
