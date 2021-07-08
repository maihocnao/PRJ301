<%-- 
    Document   : CusViewTheirBill
    Created on : Jul 6, 2021, 10:17:21 PM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Bill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%// get data from servlet (controller)
          
           //ep kieu sang result set
          
                   
           //System.out.println(arr);
          // System.out.println("JSPPPPPPPPPPPPPPP");
           //System.out.println(arr);
           Bill bill = (Bill)request.getAttribute("bill");
          // String title = request.getAttribute("tieude").toString();
           
       %>
       <h3 style="color:red;">--Check out thanh cong!--</h3>
       <table border="1">
           <thead>
               <tr>
                   <th>Bill ID</th>
                   <th>Date Create </th>
                   <th>Name</th>
                   <th>phone</th>
                   <th>Address</th>
                   <th>Total</th>
                   <th>Status</th>
                   <th>Customer ID</th>
                  <%-- <th>Update</th>
                   <th>Delete</th>--%>
                   <th>View Bill Detail</th>
               </tr>
           </thead>
           <tbody>
               
               <tr>
                   <td><%= bill.getoID() %></td>
                   <td><%= bill.getDateCreate() %></td>
                   <td><%= bill.getCname() %></td>
                   <td><%= bill.getCphone() %></td>
                   <td><%= bill.getcAddress() %></td>
                   <td><%= bill.getTotal() %></td>
                    
                    <td>
                        <%= (bill.getStatus()==1?"Enable":"Disable") %>
                    </td>
                    <td><%=bill.getCid() %></td>
                 <%--  <td><a href="ControllerBill?service=update&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">update</a></td>
                   <td><a href="ControllerBill?service=delete&pid=<%=rs.getString(1)%> &oID=<%=rs.getString(2)%>">delete</a></td>--%>
                   
                   <td><a href="BillController?service=detail&oID=<%= bill.getoID() %>">View Bill Detail</a></td>
                   
               </tr>
              
           </tbody>
       </table>
    </body>
</html>
