/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCategory;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class CategoryController extends HttpServlet {

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
        DAOCategory daoCate = new DAOCategory(dbConn);
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
                String sql = "select*from Category";
                ResultSet rs = dbConn.getData(sql);
                ArrayList<Category> arr = daoCate.listCate();
                String tittleTable = "List of Category";
                ServletConfig sc = getServletConfig();
                String title = sc.getInitParameter("title");

                
                
                
                //send data-->view
                request.setAttribute("ketQua", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", title);
                //call view (Select)
                 RequestDispatcher dis=
                        request.getRequestDispatcher("/viewCategory.jsp");
                // loclalhost:8080/webroot/filename
                // run
                dis.forward(request, response);
            }
            if(service.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
            }
            if(service.equals("update")){
                //model
                int id = Integer.parseInt(request.getParameter(""+"id"));
              //  System.out.println(id);
                ResultSet rs = dbConn.getData("select * from Category where cateID="+id);
                try {
                    if(rs.next()){
                        //setdata
                        Category cate = new Category(rs.getInt(1), 
                        rs.getString(2),rs.getInt(3));
                        request.setAttribute("cate", cate);
                        request.setAttribute("title", "update category");
                        dispath(request, response, "/updateCategory.jsp");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(service.equals("updated")){
                //code here
                
                int id = Integer.parseInt(request.getParameter("cateID"));
                String cateName= request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                System.out.println(id);
                System.out.println(cateName);
                System.out.println(status);
                int n=0;
                try {
                    daoCate.updateCategory(id, cateName, status);
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispath(request, response, "ControllerCategory?service=displayAll");
        
        
            }
            
            //delete a category
            if(service.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
                daoCate.deleteCategory(id);
                dispath(request, response, "ControllerCategory?service=displayAll");
            }
            
            // add category
            if (service.equals("addCategory")){
                // get data
                // int id = Integer.parseInt(request.getParameter("cateID"));
                String cateName= request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                Category cate = new Category(cateName, status);
                //add to dao
                daoCate.addCategory(cate);
                //
                dispath(request, response, "ControllerCategory?service=displayAll");
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
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
