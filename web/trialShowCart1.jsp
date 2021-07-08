<%-- 
    Document   : trialShowCart
    Created on : Jun 25, 2021, 1:25:58 AM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="0">
           
            <tbody>
        <%
            
            //// java.util.Enumeration em = session.getAttributeNames();
        //getkeys()
	//for(;em.hasMoreElements();){
       //// while(em.hasMoreElements()){
		//String id= em.nextElement().toString(); //get key
               Product product = (Product)request.getAttribute("product");
                //if(!id.equals("account"))
              //  Product pro=(Product)em.nextElement();
		//get value from session object (see HttpSession)
		//String count=session.getAttribute(id).toString(); //get value
                String s =" added to Shopping Cart";
//		out.println("<tr>");
//		out.println("<td>"+id+"</td>");
//		out.println("<td>"+count+"</td>");
//  		out.println("</tr>");
        %>
        
                
                   
                    <h2><%= product %>
                    <%= s%></h2>
               
            

        <%
	
        %>
        </tbody>
        </table>
        
        <h2>
            <a href="Cart1Controlller">Show Cart</a>
            
        </h2>
    </body>
</html>
