/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;
import model.DAOAdmin;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class AdminController extends HttpServlet {

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
        DBConnect dbConn = new DBConnect();
        PreparedStatement ps= null;
        DAOAdmin daoAdmin = new DAOAdmin(dbConn);
        HttpSession session = request.getSession(true);
        DAOCustomer daoCus = new DAOCustomer(dbConn);
       // String msg = null;
       // request.setAttribute("msg", msg);
        try (PrintWriter out = response.getWriter()) {
             String service = request.getParameter("service");
            if (service ==null) {
                service="displayAll";
            }
            //servlet is controller
            //MVC: view(jsp) -> action -> Controller(service)
            //--> model , choice view(jsp)
            
            if(service.equalsIgnoreCase("displayAll")) {
                //call model
                String sql = "select*from Admin";
                ResultSet rs = dbConn.getData(sql);
                ArrayList<Admin> arr = daoAdmin.listAdmin();
                String tittleTable = "List of Admin";
                //send data-->view
                request.setAttribute("ketQua", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", tittleTable);
                //call view (Select)
                 RequestDispatcher dis=
                        request.getRequestDispatcher("/viewAdmin.jsp");
                // loclalhost:8080/webroot/filename
                // run
                dis.forward(request, response);
            }
            //change password
            if (service.equals("changepassword")){
                //request.setAttribute("msg", "All Fields must not be empty");
                int id = Integer.parseInt(request.getParameter(""+"id"));
                System.out.println(id);
                ResultSet rs = dbConn.getData("select * from admin where adminID="+id);
                try {
                    if(rs.next()){
                        //setdata
                        Admin admin = new Admin(rs.getInt(1), 
                        rs.getString(2),rs.getString(3));
                        request.setAttribute("admin", admin);
                        request.setAttribute("title", "Change Password");
                        dispath(request, response, "/changePassAdmin.jsp");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //after insert pass word 
            if (service.equals("changed")) {
                String truepass="";
                String id = request.getParameter("adminID") ;
                String username = request.getParameter("username");
                String oldpass = request.getParameter("oldpass");
                String newpass = request.getParameter("newpass");
                String renewpass = request.getParameter("renewpass");
               
               
                ResultSet rs = dbConn.getData("select * from admin where adminID="+Integer.parseInt(id));
                try {
                    if(rs.next()){
                        //setdata
                       truepass= rs.getString(3);
                        //System.out.println(truepass);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (oldpass.equals(null) || oldpass=="" 
                        || newpass.equals(null)||newpass.equals("")
                        || renewpass.equals(null) || renewpass.equals(""))
                        {
                            request.setAttribute("msg", "All Fields must not be empty");
                            dispath(request, response, "ControllerAdmin?service=changepassword&id="+id);
                          //  getServletContext().getRequestDispatcher("/changePassAdmin.jsp").forward(request, response);
                           // out.println("<h3>khong duoc der trong<h3>");
                        }
                else if (!oldpass.equals(truepass)) {
                    
                      request.setAttribute("msg", "Matkhau da nhap khong dung");
                            dispath(request, response, "ControllerAdmin?service=changepassword&id="+id);
                }
                else if (!newpass.equals(renewpass)){
                    
                      request.setAttribute("msg", "Mat khau moi khong trung nhau");
                            dispath(request, response, "ControllerAdmin?service=changepassword&id="+id);
                }
                else if (newpass.length()<8){
                   
                      request.setAttribute("msg", "Chua du 8 ky tu");
                            dispath(request, response, "ControllerAdmin?service=changepassword&id="+id);
                }
                else {
                    daoAdmin.changePassword(Integer.parseInt(id), username, oldpass, newpass);
                    out.println("<h3>Successful<h3>");
                      request.setAttribute("msg", "All Fields must not be empty");
                            dispath(request, response, "ControllerAdmin?service=displayAll");
                }
            }
            if(service.equals("update")){
                //model
                int id = Integer.parseInt(request.getParameter(""+"id"));
                System.out.println(id);
                ResultSet rs = dbConn.getData("select * from admin where adminID="+id);
                try {
                    if(rs.next()){
                        //setdata
                        Admin admin = new Admin(rs.getInt(1), 
                        rs.getString(2),rs.getString(3));
                        request.setAttribute("admin", admin);
                        request.setAttribute("title", "update admin");
                        dispath(request, response, "/updateAdmin.jsp");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(service.equals("updated")){
                //code here
                int id = Integer.parseInt(request.getParameter("adminID"));
                String user = request.getParameter("username");
                //String pass = request.getParameter("password");
                System.out.println(id);
                System.out.println(user);
                 try {
                     daoAdmin.updateAdminWithoutPass(id, user);
                     
//                int id = Integer.parseInt(request.getParameter("cateID"));
//                String cateName= request.getParameter("cateName");
//                int status = Integer.parseInt(request.getParameter("status"));
//                System.out.println(id);
//                System.out.println(cateName);
//                System.out.println(status);
//                int n=0;
//                try {
//                    daoAdmin.updateCategory(id, cateName, status);
//                } catch (SQLException ex) {
//                    Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                dispath(request, response, "ControllerCategory?service=displayAll");
                 } catch (SQLException ex) {
                     Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 dispath(request, response, "ControllerAdmin?service=displayAll");
        
        
            }
            //delete a admin
            if(service.equals("delete")){
                //get id from last request
                int id = Integer.parseInt(request.getParameter("id"));
                
                // execute at dao file
                daoAdmin.deleteAdmin(id);
                //redirect to main
                dispath(request, response, "ControllerAdmin?service=displayAll");
            }
            //add admin
            if(service.equals("addAdmin")){
                //get request parameter
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Admin ad = new Admin(username, password);
                // insert dao 
                daoAdmin.addAdmin(ad);
                 //redirect to main
                dispath(request, response, "ControllerAdmin?service=displayAll");
            }
            
            if (service.equals("login")) {
                dispath(request, response, "/Login.jsp");
                

                
            }
      // login 
            if (service.equals("logined")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
               
                //System.out.println(daoCus.isCustomer(username, password));
                if ((daoAdmin.checkAdminExist(username, password)==true) || (daoCus.isCustomer(username, password)==true)) 
                {
                     String msg="ok";
                    request.setAttribute("msg", msg);
                    request.setAttribute("user", username);
                    request.setAttribute("pass", password);
                    session.setAttribute("uname", username);
                     
                    
                    
                   
                    session.setAttribute("pass", password);
                   // System.out.println("dhi4r389hr39hr93r438re");
                   // System.out.println(session.getAttribute("uname"));
                    if (daoAdmin.checkAdminExist(username, password)==true){
                    dispath(request, response, "/Detail.jsp");}
                    
                    else {
                        dispath(request, response, "ControllerProduct?service=displayAll");
                    }
                            
                }
                else {
                   dispath(request, response, "/Login.jsp");
                }
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerAdmin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerAdmin at " + request.getContextPath() + "</h1>");
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
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
