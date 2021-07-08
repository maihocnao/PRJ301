/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Product;
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
import model.DAOCart;
import model.DAOCategory;
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class ProductController extends HttpServlet {

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
        DBConnect dBConn = new DBConnect();
        PreparedStatement ps= null;
        DAOProduct daoProduct = new DAOProduct(dBConn);
        DAOCategory daoCate = new DAOCategory(dBConn);
        DAOCart daoCart = new DAOCart(dBConn);
        DAOAdmin daoAd = new DAOAdmin(dBConn);
        HttpSession session = request.getSession(true);

        ArrayList<Category> arrCate = daoCate.listCate() ;
        request.setAttribute("arrCate",arrCate);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service==null) {
                service="displayAll";
            }
            
            // display all the list of product
            if (service.equals("displayAll")) {
                 //call model
                 
                String sql = "select * from product";
                
                ResultSet rs = dBConn.getData(sql);
                
                ArrayList<Product> arr = daoProduct.list();
                String tittleTable = "List of Product";
                //send data-->view
                //System.out.println(arr);
                request.setAttribute("ketQua", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", tittleTable);
                request.setAttribute("listCate", arrCate);
                //call view (Select)
               //  RequestDispatcher dis=
                    //    request.getRequestDispatcher("/viewProduct.jsp");
                // loclalhost:8080/webroot/filename
                // run
                //dis.forward(request, response);
                try {
                 String uname = session.getAttribute("uname").toString();
                String pass = session.getAttribute("pass").toString();
                
                if(daoAd.checkAdminExist(uname, pass)){
                dispath(request, response, "/viewProductAdmin.jsp");}
                else {
                    dispath(request, response, "/viewProduct.jsp");
                }} catch(Exception e){
                    dispath(request, response, "/viewProduct.jsp");
                }
            }
            
            
            
            //update product through jsp 
            if (service.equals("update")){
                //take id parameter
                String pid = request.getParameter("pid");
                // get date from model
                ResultSet rs = dBConn.getData("Select * from Product where pid='"+
                        pid+"'");
                try {
                    while(rs.next()) {
                        String productID = rs.getString(1);
                        String pname = rs.getString(2);
                        int quan = rs.getInt(3);
                        double price = rs.getDouble(4);
                        String img = rs.getString(5);
                        String des = rs.getString(6);
                        int status = rs.getInt(7);
                        int cateID = rs.getInt(8);
                        
                        Product pnew = new Product(productID, pname, quan, price, img, des, status, cateID);
                        
                        //set attribute truyen toi jsp
                        
                        request.setAttribute("product", pnew);
                        request.setAttribute("tittleForm", "Update Product");
                        //chuyen sang jsp
                        dispath(request, response, "/updateProduct.jsp");
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
            //update into database
            if (service.equals("updated")) {
                // get data from jso
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image");
                String des = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("cateID"));
                System.out.println(pid + pname +image+des);
                // update in dao 
                daoProduct.updateProduct(pid, pname, quantity, price, image, des, status, cateID);
                
                //request back to displayAll
                dispath(request, response, "ControllerProduct?service=displayAll");
              
                
            }
            
            //delete a product by id
            if (service.equals("delete")) {
                String pid = request.getParameter("pid");
                //dao delete call
                daoProduct.deleteProduct(pid);
                
                dispath(request, response, "ControllerProduct?service=displayAll");
            }
           
           //add product 
           if (service.equals("addProduct")) {
            // get data from jso
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String image = request.getParameter("image");
                String des = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("cateID"));
                Product product = new Product(pid, pname, quantity, price, image, des, status, cateID);
                System.out.println(product);
                // update in dao 
                daoProduct.addProduct(product);
                
                //request back to displayAll
                dispath(request, response, "ControllerProduct?service=displayAll");
        }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerProduct</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerProduct at " + request.getContextPath() + "</h1>");
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
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
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
