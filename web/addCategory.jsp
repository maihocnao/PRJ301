<%-- 
    Document   : addCategory
    Created on : Jun 17, 2021, 7:40:34 PM
    Author     : Viettech88.vn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>add Category</title>
    </head>
    <body>
        <form action="ControllerCategory" method="POST">
            <table border="0">
                
                <tbody>
                    
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="cateName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <input type="radio" name="status" value="1"  />Enable
                             <input type="radio" name="status" value="0"  />Disable
                        </td>
                    </tr>
                    <tr>
                <input type="hidden" name="service" value="addCategory" />
                        <td><input type="submit" value="submit" /></td>
                        <td><input type="reset" value="reset" /></td>
                    </tr>
                </tbody>
            </table>

            
        </form>
    </body>
</html>
