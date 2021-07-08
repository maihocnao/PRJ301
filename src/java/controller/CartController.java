/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.Customer;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCart;
import model.DAOCustomer;
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                        HttpSession session = request.getSession(true);
        
        DBConnect dbconn = new DBConnect();
        DAOCart daoCart = new DAOCart(dbconn);
        DAOCustomer daoCus = new DAOCustomer(dbconn);
        DAOProduct daoProduct = new DAOProduct(dbconn);
        DAOBill daoBill = new DAOBill(dbconn);
        DAOBillDetail daoBD = new DAOBillDetail(dbconn);
        String msg="";
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service==null) {
                service="displayAll";
                
            }
            
            //display all product in cart
//            if (service.equals("displayAll")){
//                ArrayList<Cart1> cart = daoCart.cartList();
//                 ArrayList<Product> arr = daoProduct.list();
//                 request.setAttribute("productList", arr);
//                System.out.println(cart);
//                String tieude = "Trong gio hang co";
//                request.setAttribute("tieude", tieude);
//                request.setAttribute("cartlist", cart);
//                dispath(request, response, "/showCart.jsp");
//                
//            }
            
            if (service.equals("delete")){
                String pID = request.getParameter("pID");
                daoCart.deleteByProduct(pID);
                dispath(request, response, "ControllerCart?service=displayAll");
            }
            
            if (service.equals("deleteAll")) {
                daoCart.deleteAll();
                dispath(request, response, "ControllerCart?service=displayAll");
            }
            
            if (service.equals("add2cart")){
                String id = request.getParameter("pid"); 
                System.out.println(id);
                if (daoCart.checkProductExistInCart(id)==false) {
                    daoCart.addCart(id, 0);
                }
                
    // id - key
        //get infor of product: name, price, images
        // create product 
	         Object value = session.getAttribute(id);
    //getKey(id)
     //   Product pro=(Product)session.getAttribute(id);
	//the first time the product is selected
	if(value==null){
            //set quantity of product is 1
		//put name-value pair into session object (see HttpSession)
		session.setAttribute(id,daoCart.returnQuantity(id)+1); // put(key,value)
                 daoCart.updateQuantity(id,  daoCart.returnQuantity(id)+1);
              //  pro.setQuantity(1);
                //session.setAttribute(id,pro);
	}
	//the second/third... time the product is selected
	else{
		int count = daoCart.returnQuantity(id)+1;
               // pro.setQuantitty(pro.getQuantity()+1);
		//put name-value pair into session object (see HttpSession)
		session.setAttribute(id,String.valueOf(count));
                daoCart.updateQuantity(id, count);
                //session.setAttribute(id,pro);
                
                
		
	}
        request.setAttribute("msg", id+"added to shopping cart");
        request.setAttribute("id", id);
                //System.out.println(id);
              //  System.out.println(id+""+session.getAttribute(id).toString());
        dispath(request, response, "/trialShowCart.jsp");
            }
            
             if (service.equals("add2Cart")) {
                 String uname=null,pass=null;
                 try {
                  uname = session.getAttribute("uname").toString();
                   pass = session.getAttribute("pass").toString();
                 } catch(Exception e){
                    request.setAttribute("msg", "Ban phai dang nhap thi moi duoc them vao gio hang");
                    dispath(request, response, "Login.jsp");
                }
                
                
                 String id = request.getParameter("pid"); 
                 
                  Object value = session.getAttribute(id);
   
                        if(value==null){
           
                            session.setAttribute(id,1); // put(key,value)
                
                                }
	//the second/third... time the product is selected
                        else{
                                int count = Integer.parseInt(value.toString())+1;

              
                                    session.setAttribute(id,String.valueOf(count));
        
                                }
                            request.setAttribute("msg", id+"added to shopping cart");
                            request.setAttribute("id", id);
               
                            dispath(request, response, "ControllerProduct?service=displayAll");
            }
             
    //remove a product from cart
            if (service.equals("remove1")){
                String id = request.getParameter("id");
                session.removeAttribute(id);
                dispath(request, response, "/showCart1.jsp");
                
            }
    //remove all session       
            if (service.equals("removeAll")){
                java.util.Enumeration em = session.getAttributeNames();
        //getkeys()
	//for(;em.hasMoreElements();){
        while(em.hasMoreElements()){
		String id= em.nextElement().toString(); 
		session.removeAttribute(id);
                
	}
        dispath(request, response, "/showCart1.jsp");
            }
//check out   
            if (service.equals("checkout")){
                
                System.out.println("hahaha");
                String name=request.getParameter("name");
                String phone=request.getParameter("phone");
                String address=request.getParameter("address");
                //String cid=request.getParameter("cid");
               // int cID = Integer.parseInt(cid);
                double total=Double.parseDouble(request.getParameter("total"));
               // String orderID = daoBill.makeOID(cID);
                
               // daoBill.addNewBill(orderID, name, phone, address, total , 0, cID);
               // System.out.println(cid+name+phone+address);
                
                ArrayList<Product> arrPro= new  ArrayList<>();
                Product pro = new Product();
                String count="0";
                String uname=null,pass=null;
                 try {
                  uname = session.getAttribute("uname").toString();
                   pass = session.getAttribute("pass").toString();
                 } catch(Exception e){
                    dispath(request, response, "/viewProduct.jsp");
                }
                if (uname.equals(null)&&pass.equals(null)){
                    session.setAttribute("checkout", "ok");
                    dispath(request, response, "Login.jsp");
                }
               
                
                Customer cus = daoCus.getCusByUserName(uname);
                int cusid = cus.getCid();
                String orderID = daoBill.makeOID(cusid);
                daoBill.addNewBill(orderID, name, phone, address, total , 0, cusid);
                java.util.Enumeration em = session.getAttributeNames();
                while(em.hasMoreElements()){
                        String id= em.nextElement().toString(); //get key
                  
                        
                        if ((id.equals("WELD_S_HASH")!=true)&&(id.equals("org.jboss.weld.context.conversation.ConversationIdGenerator")!=true)
                        &&(id.equals("org.jboss.weld.context.ConversationContext.conversations")!=true)&&id.equals("uname")!=true&&id.equals("pass")!=true)
                            {
                            //if(!id.equals("account"))
                             //  Product pro=(Product)em.nextElement();
                                //get value from session object (see HttpSession)
                                    count=session.getAttribute(id).toString(); //get value
                                    pro = daoProduct.getProductByID(id);
                                    arrPro.add(pro);
                                    int value = Integer.parseInt(count);
                                    
                                    daoBD.addNewBD(id, orderID, value, pro.getPrice(), value*pro.getPrice());
                    
                            }
                   
                        }
                Bill theBill = daoBill.getBillByOID(orderID);
                request.setAttribute("bill", theBill);
                dispath(request, response, "/CusViewTheirBill.jsp");
                //out.println("<h3>Successful!</h3>");
                }
            
            

        }
    }
    private void dispath(HttpServletRequest request, HttpServletResponse response, String URL) {
        try {
            RequestDispatcher dis = request.getRequestDispatcher(URL);
            // url: link to view file start with /
            dis.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
