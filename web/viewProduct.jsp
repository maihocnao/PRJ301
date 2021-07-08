<%-- 
    Document   : viewProduct
    Created on : Jun 16, 2021, 1:53:55 PM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet)request.getAttribute("ketQua");
            ArrayList<Product> arr = (ArrayList<Product>)request.getAttribute("list");
            String tittle = request.getAttribute("tieude").toString();
            ArrayList<Category> arrCate = (ArrayList<Category>)request.getAttribute("listCate");
        %>
        <caption><%=tittle%></caption>
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category</th>
                   <%-- <th>Update</th>
                    <th>Delete</th> --%>
                    <th>Add to Cart</th>
                    
                </tr>
            </thead>
            <tbody>
                <%
                    while(rs.next()){
//                        System.out.println(rs.getString(1));
//                        System.out.println(rs.getString(2));
//                        System.out.println(rs.getInt(3));
//                        System.out.println(rs.getDouble(4));
//                        System.out.println(rs.getString(5));
//                        System.out.println(rs.getString(6));
//                        System.out.println(rs.getInt(7));
//                        System.out.println(rs.getInt(8));
                %>
                <tr>
                    <td><%= rs.getString(1) %></td>
                    <td><%= rs.getString(2) %></td>
                    <td><%= rs.getInt(3) %></td>
                    <td><%= rs.getDouble(4) %></td>
                    <td> <img src="<%= rs.getString(5)%>" width="70" height="70" alt="Product"/></td>
                    
           

                    <td><%=rs.getString(6) %></td>
                    <td>
                        <%= (rs.getInt(7)==1?"Enable":"Disable") %>
                    </td>
                    <%
                        int a = rs.getInt(8);
                        String catename = ""+a;
                        for (Category cate: arrCate) {
                            if (cate.getCateID()==rs.getInt(8)){
                             catename = cate.getCateName() ;}
                        }
                    %>
                    <td><%= catename %></td>
                   <%-- <td><a href="ControllerProduct?service=update&pid=<%= rs.getString(1) %>">Update</a></td>
                    <td><a href="ControllerProduct?service=delete&pid=<%= rs.getString(1) %>">Delete</a></td> --%>
                   <td><a href="ControllerCart?service=add2Cart&pid=<%= rs.getString(1) %>">Add to cart</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            <h3><a href="/SE1507_MVC/showCart1.jsp">Show Cart</a></h3>
            <h3><a href="/SE1507_MVC/addProduct.jsp">Add new Product</a></h3>
    </body>
</html>
