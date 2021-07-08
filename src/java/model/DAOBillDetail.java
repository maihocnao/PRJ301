/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
import entity.Category;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Viettech88.vn
 */
public class DAOBillDetail {
    Connection conn = null;
     DBConnect dbconn=null;

    public DAOBillDetail(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn=dbconn;
    }
    
    public int addBillDetail(BillDetail billDe){
        int n = 0;
         String preSql="insert into BillDetail(pid,oID,quantity,price,total)"
                 + " values (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, billDe.getPid());
            pre.setString(2, billDe.getoID());
            pre.setInt(3, billDe.getQuantity());
            pre.setDouble(4, billDe.getMoney());
            pre.setDouble(5, billDe.getTotal());
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return n;
       
        
    }
    
    
    public int changeQuantity(int newQuant, String pID, String oID) {
        int n = 0;
        String preSql = "update BillDetail set quantity = ?where pid=? and oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setInt(1, newQuant);
            pre.setString(2, pID);
            pre.setString(3,oID );
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAllBillDetail(){
        String query= "Select * from BillDetail";
        ResultSet rs = dbconn.getData(query);
         try {
             while (rs.next()) {
                 String pid= rs.getString(1),  oID=rs.getString(2);
                 int quantity=rs.getInt(3);
                 double money=rs.getDouble(4),
                         total=rs.getDouble(5);
                 BillDetail bd= new BillDetail(pid, oID, quantity, money, total);
                 
                 System.out.println(bd);
             }
         } catch (SQLException ex) {
             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void getBillDetailByBillID(String BillID) {
        String query = "select * from BillDetail\n"
                + "where oid = '"+BillID+"'";
        ResultSet rs=dbconn.getData(query);
        
            
        try {
             while (rs.next()) {
                 String pid= rs.getString(1),  oID=rs.getString(2);
                 int quantity=rs.getInt(3);
                 double money=rs.getDouble(4),
                         total=rs.getDouble(5);
                 BillDetail bd= new BillDetail(pid, oID, quantity, money, total);
                 
                 System.out.println(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void getBillDetailByProductID(String ProductID) {
        String query = "select * from BillDetail\n"
                + "where pid = '"+ ProductID+"'";
        ResultSet rs=dbconn.getData(query);
        
            
        try {
             while (rs.next()) {
                 String pid= rs.getString(1),  oID=rs.getString(2);
                 int quantity=rs.getInt(3);
                 double money=rs.getDouble(4),
                         total=rs.getDouble(5);
                 BillDetail bd= new BillDetail(pid, oID, quantity, money, total);
                 
                 System.out.println(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
     public void getBillDetailByQuantity(int q) {
        String query = "select * from BillDetail"
                + "where quantity = "+q+"";
        ResultSet rs=dbconn.getData(query);
        
            
        try {
             while (rs.next()) {
                 String pid= rs.getString(1),  
                         oID=rs.getString(2);
                 int quantity=rs.getInt(3);
                 double money=rs.getDouble(4),
                         total=rs.getDouble(5);
                 BillDetail bd= new BillDetail(pid, oID, quantity, money, total);
                 
                 System.out.println(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public ArrayList<BillDetail> list() {
        ArrayList<BillDetail> arr= new ArrayList<BillDetail>();
        ResultSet rs = dbconn.getData("select*from BillDetail");
        try {
            while(rs.next()){
//                Category cate = new Category(rs.getInt(1), 
//                        rs.getString(2),rs.getInt(3));
                  BillDetail bd = new BillDetail(rs.getString(1),rs.getString(2), rs.getInt(3), 
                          rs.getDouble(4), rs.getDouble(5));
                  
                 // System.out.println(bd);
                arr.add(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public int updateBD(String pid, String oID, int quant, double price){
        int n =0;
        double total=quant *price;
        String query ="update BillDetail set pid=?,oid=?,quantity=?,"
                + "price=?, total=? where pid=? and oid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(query);
            pre.setString(1, pid);
            pre.setString(2, oID);
            pre.setInt(3, quant);
            pre.setDouble(4, price);
            pre.setDouble(5, total);
            pre.setString(6, pid);
            pre.setString(7, oID);
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public void deleteBD (String pid, String oid) {
        String query="delete from BillDetail where pid=? and oid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(query);
            pre.setString(1, pid);
            pre.setString(2, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //
    public void addNewBD(String pid, String oID,int quantity, double price, double total){
        String preSql="insert into BillDetail(pid,oID,quantity,price,total)"
                 + " values (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, pid);
            pre.setString(2, oID);
            pre.setInt(3, quantity);
            pre.setDouble(4, price);
            pre.setDouble(5, total);
             pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOBillDetail dao = new DAOBillDetail(dbconn);
        dao.addNewBD("a1", "b3", 3, 22, 66);
       // dao.updateBD("a1", "b1", 3, 13455);
        //dao.list();
//        int n = dao.addBillDetail(new BillDetail("a2", "b4",5, 34, 454));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
    }
}
