<%-- 
    Document   : updateBillDetail
    Created on : Jun 18, 2021, 6:50:30 AM
    Author     : Viettech88.vn
--%>

<%@page import="entity.BillDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            BillDetail bd = (BillDetail)request.getAttribute("billdetail");
            String tieude = request.getAttribute("tieude").toString();
            
            
        %>
        
        <title><%=tieude %></title>
        <form action="ControllerBillDetail" method="POST">
            <table border="0">
               
                <tbody>
                    <tr>
                        <td>Product ID</td>
                        <td><input type="text" name="pid" value="<%= bd.getPid() %>" /></td>
                    </tr>
                    <tr>
                        <td>Bill ID</td>
                        <td><input type="text" name="oid" value="<%= bd.getoID() %>" /></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantity" value="<%= bd.getQuantity() %>" /></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price" value="<%= bd.getMoney() %>" /></td>
                    </tr>
                    <tr>
                             <input type="hidden" name="service" value="updated" />
                             <td><input type="submit" value="Update" /></td>
                             <td><input type="reset" value="reset" /></td>
                    </tr>
                </tbody>
            </table>
            
            

            
        </form>
        
    </body>
</html>
