<%-- 
    Document   : updateProduct
    Created on : Jun 16, 2021, 1:54:36 PM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
    <body>
       <%
           Product product = (Product)request.getAttribute("product");
           
           String tittle = request.getAttribute("tittleForm").toString();
           ArrayList<Category> arrCate = (ArrayList < Category >)request.getAttribute("arrCate");
       %>
       <h3><caption><%= tittle %></caption></h3>
    <form action="ControllerProduct" method="POST">
         <table border="0">
        
        <tbody>
            <tr>
                <td>Product ID</td>
                <td><input type="text" name="pid" value="<%= product.getPid() %>" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="pname" value="<%= product.getPname() %>" /></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity" value="<%= product.getQuantity() %>" /></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="<%= product.getPrice() %>" /></td>
            </tr>
            <tr>
                <td>Image</td>
                <td><input type="text" name="image" value="<%= product.getImage() %>" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" value="<%= product.getDescription() %>" /></td>
            </tr>
            
          <%--  <tr><td>Cate ID</td>
                <td><input type="text" name="cateID" value="<%= product.getCateID() %>" /></td></tr>
                --%>
                <tr>
                    <td>
                        Choose Category </td>
                    <td> <select name="cateID">
            <% if(arrCate.size() >0) {
                for (Category cate: arrCate) {
            
                %>
            <option value="<%= cate.getCateID()%>"> 
                <%= cate.getCateName() %>
            </option>
                <%} }%>    
                
                        </select></td>
         </tr>
         <tr>
                <td>Status</td>
                <td>
                    <input type="radio" name="status" value="1" <%= product.getStatus()==1?"checked":""%> />Enable
                    <input type="radio" name="status" value="0" <%= product.getStatus()==0?"checked":""%> />Disable
                </td>
            </tr>
            <tr>
                 <input type="hidden" name="service" value="updated" />
                 <td><input type="submit" value="Update" /></td>
                 <td><input type="reset" value="Reset" /></td>
            </tr>
            
        </tbody>
    </table>
            
    </form>

    </body>
</html>
