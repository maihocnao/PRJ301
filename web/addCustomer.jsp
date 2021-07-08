<%-- 
    Document   : addCustomer
    Created on : Jun 17, 2021, 7:40:50 PM
    Author     : Viettech88.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControllerCustomer" method="POST">
            
            <table border="0">
                
                <tbody>
                    
                    <tr>
                        <td>Name</td>
                        
                        <td><input type="text" name="cname" value=""> </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" name="cphone" value=""> </td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="caddress" value=""></td>
                    </tr>
                    <tr>
                        <td>username</td>
                        <td><input type="text" name="username" value=""></td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><input type="password" name="password" value=""></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                       
                        <td>
                            <input type="radio" name="status" value ="1" > Enable
                            <input type="radio" name="status" value ="0"  > Disable
                           
                        </td>
                    </tr>
                     
                    <input type="hidden" name="service" value="addCustomer">
                    <td><input type="submit" value="update"></td>
                    <td><input type="reset" value="reset"></td>
                    </tr>
                </tbody>
            </table>


        </form>
    </body>
</html>
