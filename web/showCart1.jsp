<%-- 
    Document   : showCart1
    Created on : Jul 6, 2021, 2:52:15 AM
    Author     : Viettech88.vn
--%>

<%@page import="entity.Product"%>
<%@page import="model.DBConnect"%>
<%@page import="model.DAOProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  DBConnect dbconn = new DBConnect();
            DAOProduct dao = new DAOProduct(dbconn);
            Product pro = new Product();
            String count="0";
            double total=0;
        %>
        
        <form action="ControllerCart" method="POST">
        <table border="1">
            <thead>
                <tr>
                    
                    <th>Pid</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Remove</th>
                    
                </tr>
            </thead>
            <tbody>
                
      <%  java.util.Enumeration em = session.getAttributeNames();
        //getkeys()
	//for(;em.hasMoreElements();){
        String uname=null;
                
                try {
                  uname = session.getAttribute("uname").toString();
                   System.out.println(uname+"UANEMMMMMMMM");
                 } catch(Exception e){
                    
                }
        while(em.hasMoreElements()){
		String id= em.nextElement().toString(); //get key
                
                System.out.println(id);
                System.out.println("DDDDDDDDDDDDDDD");
                if ((id.equals("WELD_S_HASH")!=true)&&(id.equals("org.jboss.weld.context.conversation.ConversationIdGenerator")!=true)
                        &&(id.equals("org.jboss.weld.context.ConversationContext.conversations")!=true)&&id.equals("uname")!=true&&id.equals("pass")!=true)
                {
                //if(!id.equals("account"))
              //  Product pro=(Product)em.nextElement();
		//get value from session object (see HttpSession)
		 count=session.getAttribute(id).toString(); //get value
                 
                pro = dao.getProductByID(id);
                int value = Integer.parseInt(count);
                double money = pro.getPrice();
                %>
                <tr>
                    
                    <td><%= pro.getPid() %></td>
                    <td><%= pro.getPname() %></td>
                    <td><input name="quantity" type="number" value="<%= count %>"></td>
                    
                    <td><%= money %></td>
                    <td><%= money*value %></td>
                    <td><a href="ControllerCart?service=remove1&id=<%= id %>">Remove</a></td>
                    <% total+= money*value  ;%>
                    
                    
                </tr>
                <%}}%>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><%= total %></td>
            <input type="hidden" name="total" value="<%=total%>" />
                </tr>
            </tbody>
            
        </table>
                
                <h3>Nhap thong tin dia chi nhan hang</h3>
                <table border="0">
                   
                    <tbody>
                        <tr>
                            <td>Name</td>
                            <td><input type="text" name="name" value="" /></td>
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td><input type="text" name="phone" value="" /></td>
                        </tr>
                        <tr>
                            <td>Address</td>
                            <td><input type="text" name="address" value="" /></td>
                        </tr>
                        
                    </tbody>
                </table>

                <h3><a href="ControllerProduct?service=displayAll">List Product</a></h3>
                <h3><a href="ControllerCart?service=removeAll">Remove All</a></h3>
                 <tr>
                <input type="hidden" name="service" value="checkout" />
                        <td><input type="submit" value="checkout" /></td>
                        
                    </tr>
                
        </form>
    </body>
</html>
