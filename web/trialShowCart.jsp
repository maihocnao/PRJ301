<%-- 
    Document   : trialShowCart
    Created on : Jun 25, 2021, 1:25:58 AM
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
        <table border="0">
           
            <tbody>
        <%
            
            //// java.util.Enumeration em = session.getAttributeNames();
        //getkeys()
	//for(;em.hasMoreElements();){
       //// while(em.hasMoreElements()){
		//String id= em.nextElement().toString(); //get key
                String id = request.getAttribute("id").toString();
                //if(!id.equals("account"))
              //  Product pro=(Product)em.nextElement();
		//get value from session object (see HttpSession)
		String count=session.getAttribute(id).toString(); //get value
                String s =" added to Shopping Cart";
//		out.println("<tr>");
//		out.println("<td>"+id+"</td>");
//		out.println("<td>"+count+"</td>");
//  		out.println("</tr>");
        %>
        
                
                   
                    <h3><%= id%>
                    <%= s%></h3>
               
            

        <%
	
        %>
        </tbody>
        </table>
        
        <h3>
            <a href="CartController">Show Cart</a>
        </h3>
    </body>
</html>
