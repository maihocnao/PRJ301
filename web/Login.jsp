<%-- 
    Document   : Login
    Created on : Jun 21, 2021, 1:00:57 PM
    Author     : Viettech88.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            String msg = "";
            try {
               msg= request.getAttribute("msg").toString();
            }catch(Exception e){
                
            }
        %>
 <h3><%= msg%></h3>
        <form action="ControllerAdmin" method="POST" autocomplete="on"> 
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                   
                    <tr>
                 
                <input type="hidden" name="service" value="logined" />
                <td></td>
                <td><input type="submit" value="login" /></td>
                    </tr>
                </tbody>
            </table>

       </form>
        
    </body>
</html>
