/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Customer;
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
import model.DAOBill;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author Viettech88.vn
 */
public class BillController extends HttpServlet {

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
        DAOBill daoBill = new DAOBill(dBConn);
        DAOCustomer daoCus = new DAOCustomer(dBConn);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            //when service equal null, display all the list of bill
            if (service == null) {
                service="displayAll";
            }
            
            //display all
            if (service.equals("displayAll")) {
                String sql = "select * from Bill";
                ResultSet rs = dBConn.getData(sql);
                ArrayList<Bill> arr = daoBill.list();
             
                //System.out.println(arr);
                // other data
                String tittle = "List of Bill";
                // set data for view
                request.setAttribute("ketQua", rs);
                request.setAttribute("list", arr);
                request.setAttribute("tieude", tittle);
                // call view 
                // RequestDispatcher dis=request.getRequestDispatcher(URL);
                // URL: jsp, servlet, syntag:  /name
               // RequestDispatcher dis = request.getRequestDispatcher("/viewBillDetail.jsp");
                // run
                dispath(request, response,"/viewBill.jsp");
            }
            
            
    // get Bill detail        
            if (service.equals("detail")) {
                String oID = request.getParameter("oID");
                int cid=0 ;
                String sql="  select * from Bill\n" +
                            "  inner join BillDetail\n" +
                            "  on bill.oID =BillDetail.oID\n" +
                            "  where BillDetail.oID='"+ oID+"'";
                ResultSet rs = dBConn.getData(sql);
                
                try {
                    while(rs.next()){
                        cid=rs.getInt("cid");
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Customer cus = daoCus.getCusByID(cid);
                String cname = cus.getCname();
                ArrayList<BillDetail> arrBD = daoBill.listBD(oID);
                double total = 0;
                for (BillDetail bd: arrBD){
                    bd.setTotal(bd.getMoney()*bd.getQuantity());
                    total+= bd.getTotal();
                }
                System.out.println(total);
                daoBill.changeTotal(oID, total);
                //System.out.println(arrBD);
                 // other data
                String tittle = "Bill Detail";
                // set data for view
                //request.setAttribute("ketQua", rs);
                request.setAttribute("ketQua", rs);
                request.setAttribute("cname", cname);
                request.setAttribute("total", total);
                request.setAttribute("list", arrBD);
                request.setAttribute("tieude", tittle);
                dispath(request, response, "/viewDetail.jsp");
                
            }
            if (service.equals("update")) {
                
            }
            
            if (service.equals("updated")) {
                
            }
            
            if (service.equals("delete")) {
                
            }
            
            
            
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerBill</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerBill at " + request.getContextPath() + "</h1>");
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
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
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
