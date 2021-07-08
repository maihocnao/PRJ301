<%-- 
    Document   : updateCategory
    Created on : Jun 14, 2021, 2:36:43 AM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Category cate = (Category)request.getAttribute("cate");
            String title =request.getAttribute("title").toString();
        %>
        <form action="ControllerCategory" method="POST">
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>ID</td>
                        <input type="hidden" name="cateID" value="<%=cate.getCateID()%>" />
                       <%-- <td type="text" name="cateID" value="<%=cate.getCateID()%>"><%=cate.getCateID()%> </td>--%>
                        <td><%=cate.getCateID() %></td>
                
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="cateName" value="<%=cate.getCateName()%>" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <input type="radio" name="status" value="1" <%=(cate.getStatus()==1?"checked":"")%> />Enable
                             <input type="radio" name="status" value="0" <%=(cate.getStatus()==0?"checked":"")%> />Disable
                        </td>
                    </tr>
                    <tr>
                <input type="hidden" name="service" value="updated" />
                        <td><input type="submit" value="update" /></td>
                        <td><input type="reset" value="reset" /></td>
                    </tr>
                </tbody>
            </table>

            
        </form>
    </body>
</html>
