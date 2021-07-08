<%-- 
    Document   : addAdmin
    Created on : Jun 17, 2021, 7:41:12 PM
    Author     : Viettech88.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>add Admin</title>
    </head>
    <body>
        <h3>Add Admin</h3>
        <form action="ControllerAdmin" method="post">
            <table border="0">
                
                <tbody>
                    <tr>
                        <td>username</td>
                        <td><input type="text" name="username" value="" /></td>
                        
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><input type="text" name="password" value="" /></td>
                    </tr>
                    
                    <tr>
                        <input type="hidden" name="service" value="addAdmin" />
                        <td> <input type="submit" value="Add Admin" /></td>
                        <td> <input type="reset" value="Reset" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
