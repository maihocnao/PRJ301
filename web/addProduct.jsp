<%-- 
    Document   : addProduct
    Created on : Jun 17, 2021, 7:40:59 PM
    Author     : Viettech88.vn
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="model.DAOCategory"%>
<%@page import="model.DAOProduct"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="model.DBConnect"%>
<%@page import="model.DBConnect"%>
<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         DBConnect dBConn = new DBConnect();
        PreparedStatement ps= null;
        DAOProduct daoProduct = new DAOProduct(dBConn);
        DAOCategory daoCate = new DAOCategory(dBConn);
         String sqlCate = "select * from Category";
                ResultSet rsCate = dBConn.getData(sqlCate);
                ArrayList<Category> arrCate = daoCate.listCate() ;
         System.out.println(arrCate);
        %>
        <h2>Add Product</h2>
        <form action="ControllerProduct" method="POST">
         <table border="0">
        
        <tbody>
            <tr>
                <td>Product ID</td>
                <td><input type="text" name="pid" value="" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="pname" value="" /></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity" value="" /></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="" /></td>
            </tr>
            <tr>
                <td>Image</td>
                <td><input type="text" name="image" value="" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" value="" /></td>
            </tr>
            
            <tr>
                <td> Category</td>
                <td>    
           <select name="cateID">
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
                    <input type="radio" name="status" value="1" />Enable
                    <input type="radio" name="status" value="0"  />Disable
                </td>
            </tr>
            
            <tr>
                 <input type="hidden" name="service" value="addProduct" />
                 <td><input type="submit" value="Submit" /></td>
                 <td><input type="reset" value="Reset" /></td>
            </tr>
            
        </tbody>
    </table>
    </form>
    </body>
</html>
