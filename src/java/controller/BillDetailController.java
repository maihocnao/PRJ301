/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.BillDetail;
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
import model.DAOBillDetail;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class BillDetailController extends HttpServlet {

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
        DAOBillDetail daoBD = new DAOBillDetail(dBConn);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service==null){
                service="displayAll";
                
            }
            //display all the bill detail 
            if (service.equals("displayAll")){
                /* MVC
                view -- action-- controller
                controller -- call model -- set data for view
                -- call view
                 */
                // call model
                String sql = "select * from BillDetail";
                ResultSet rs = dBConn.getData(sql);
                ArrayList<BillDetail> arr = daoBD.list();
               // System.out.println("notice me senpai");
                System.out.println(arr);
                // other data
                String tittle = "List of BillDetail";
                // set data for view
                request.setAttribute("ketQua", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", tittle);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
               // RequestDispatcher dis = request.getRequestDispatcher("/viewBillDetail.jsp");
                // run
                dispath(request, response,"/viewBillDetail.jsp");
            }
            //chuyen tiep sang update jsp
            if (service.equals("update")) {
                String pid = request.getParameter("pid");
                String oid = request.getParameter("oID");
                //load data to jsp 
                String query="select * from BillDetail where pid='"+pid+"'and oid='"+oid+"'";
                ResultSet rs = dBConn.getData(query);
                
                try {
                    if (rs.next()) {
                        BillDetail bd = new BillDetail(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
                        //System.out.println(bd);
                        request.setAttribute("billdetail", bd);
                        request.setAttribute("tieude", "Update Bill Detail");
                        dispath(request, response, "/updateBillDetail.jsp");
                        
                    }
                        
                        
                        } catch (SQLException ex) {
                    Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
            //after get data update from update jsp
            if (service.equals("updated")) {
                String productID = request.getParameter("pid");
                String billID = request.getParameter("oid");
                int quant = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                
                System.out.println(productID+billID+quant+price);
                //update dao
                daoBD.updateBD(productID, billID, quant, price);
                //dispath
                dispath(request, response,"ControllerBillDetail?service=displayAll");
            }
            //delete a bill 
            
            if (service.equals("delete")){
                String productID = request.getParameter("pid");
                String billID = request.getParameter("oID");
                daoBD.deleteBD(productID, billID);
                 dispath(request, response,"ControllerBillDetail?service=displayAll");
            }
            
            
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerBillDetail</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerBillDetail at " + request.getContextPath() + "</h1>");
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
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
