/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class CustomerController extends HttpServlet {

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
        DBConnect dbconn = new DBConnect();
        DAOCustomer dao = new DAOCustomer(dbconn);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                /* MVC
                view -- action-- controller
                controller -- call model -- set data for view
                -- call view
                 */
                // call model
                String sql = "select * from Customer";
                ResultSet rs = dbconn.getData(sql);
                ArrayList<Customer> arr = dao.list();
                // other data
                String title = "List of customer";
                // set data for view
                request.setAttribute("rs", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
                RequestDispatcher dis = request.getRequestDispatcher("/viewCustomer.jsp");
                // run
                dis.forward(request, response);

            }
            
            if (service.equals("update")) {
                //get submit-- check
                //model
                ResultSet rs = dbconn.getData(""
                        + "select * from Customer where cid="
                        + Integer.parseInt(request.getParameter("id")));
                try {
                    if (rs.next()) {
                        int cid = rs.getInt("cid");//rs.getInt(2);
                        String cname = rs.getString(2);//rs.getString("cname");
                        String cphone = rs.getString("cphone"),
                                cAddress = rs.getString(4),
                                username = rs.getString(5),
                                password = rs.getString(6);
                        int status = rs.getInt(7);
                        Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                        // set data
                        request.setAttribute("cus", cus);
                        request.setAttribute("formtitle", "update Customer");
                        // call view
                        RequestDispatcher dis = 
                                request.getRequestDispatcher("/updateCustomer.jsp");
                        // run
                        dis.forward(request, response);
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(service.equals("updated")){
                int cid = Integer.parseInt(request.getParameter(""+"cid"));
                String cname= request.getParameter("cname");
                String cphone = request.getParameter("cphone");
                String caddress = request.getParameter("caddress");
                String username = request.getParameter("username");
                //String password = request.getParameter("password");
                int status = Integer.parseInt(request.getParameter("status"));
                System.out.println(cid);
                System.out.println(cname);
                
                dao.updateCustomerWithouPassword(cid, cname, cphone, caddress, username, status);
                
                dispath(request, response, "ControllerCustomer?service=displayAll");
                
            }
//delete a customer            
            if (service.equals("delete")) {
                //code
                int id = Integer.parseInt( request.getParameter("id"));
                dao.deleteCustomer(id);
                dispath(request, response, "ControllerCustomer?service=displayAll");
            }
    //insert new Customer 
            if (service.equals("addCustomer")) {
                //getParameter
                String cname= request.getParameter("cname");
                String cphone = request.getParameter("cphone");
                String caddress = request.getParameter("caddress");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int status = Integer.parseInt(request.getParameter("status"));
                Customer cus = new Customer(cname, cphone, caddress, username, password, status);
                //dao work
                dao.addCustomer(cus);
                
                
                dispath(request, response, "ControllerCustomer?service=displayAll");
            }
 //chang pass word           
            if (service.equals("changepass")){
                int cusID = Integer.parseInt(request.getParameter("id"));
              Customer cus = dao.getCusByID(cusID);
                System.out.println(cus);
               
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerCustomer</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerCustomer at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }
     private void dispath(HttpServletRequest request, HttpServletResponse response, String URL) {
        try {
            RequestDispatcher dis = request.getRequestDispatcher(URL);
            // url: link to view file start with /
            dis.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
