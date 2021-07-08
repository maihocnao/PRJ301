<%-- 
    Document   : showCart
    Created on : Jun 23, 2021, 9:49:48 PM
    Author     : Viettech88.vn
--%>

<%@page import="model.DBConnect"%>
<%@page import="entity.Product"%>
<%@page import="entity.Cart1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DAOProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String tieude = request.getAttribute("tieude").toString();
            ArrayList<Cart1> arr = (ArrayList<Cart1>)request.getAttribute("cartlist");
             ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
              DBConnect dBConn = new DBConnect();
             DAOProduct dao = new DAOProduct(dBConn);
             double total = 0;
             java.util.Enumeration em = session.getAttributeNames();
      
        
        %>
        
      <%--  <h3><%=tieude %></h3>--%>
        <table border="1">
            <thead>
                <tr>
                    
                    <th>Pid</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Remove</th>
                    
                </tr>
            </thead>
            <tbody>
                <%
                    for(Cart1 cart:arr ){
                        
                    
                %>
                <tr>
                    
                    <td><%= cart.getpID() %></td>
                    <td><%= dao.getPName(cart.getpID()) %></td>
                    <td><%= cart.getQuantity() %></td>
                    <td><%= dao.getPPrice(cart.getpID()) %></td>
                    <td><%= dao.getPPrice(cart.getpID()) *cart.getQuantity() %></td>
                    <% total+=  dao.getPPrice(cart.getpID()) *cart.getQuantity();%>
                    <td><a href="ControllerCart?service=delete&pID=<%= cart.getpID() %>">Remove</a></td>
                    
                </tr>
                <%}%>
            </tbody>
            
        </table>
                <%--   <h2>Total <%=total%> </h2>--%>
                <h3> <a href="ControllerCart?service=deleteAll">Delete all from Cart</a></h3>
            
                <h3><a href="ControllerProduct" target="_blank" >Items List</h3>
                
            <h3><a href="checkout.jsp">Check-out</h3>

    </body>
</html>
