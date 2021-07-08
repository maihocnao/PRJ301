/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Bill;
import entity.BillDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Viettech88.vn
 */
public class DAOBill {
    Connection conn = null;
     DBConnect dbconn=null;

    public DAOBill(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn=dbconn;
    }

    //not yet add Date Create
    public int addBill(Bill bill) {
        int n = 0;
        String preSql = "insert into Bill(oID,cname,cphone,cAddress,total,status,cid)" +
"values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, bill.getoID());
            
            pre.setString(2, bill.getCname());
            pre.setString(3, bill.getCphone());
            pre.setString(4, bill.getcAddress());
            pre.setDouble(5, bill.getTotal());
            pre.setInt(6, bill.getStatus());
            
            pre.setInt(7, bill.getCid());
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int changeStatus (int newStatus, String oID) {
        int n=0;
        String preSql="update Bill set status=? where  oID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setInt(1, newStatus);
            pre.setString(2, oID);
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public void changeTotal(String billID, double getTotal) {
        String query ="update Bill set total=? where oID =?";
        System.out.println(query);
        try {
            PreparedStatement pre = conn.prepareStatement(query);
            pre.setString(2, billID);
            pre.setDouble(1, getTotal);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void displayAllBill(){
        String query= "Select * from Bill";
        ResultSet rs = dbconn.getData(query);
         try {
             while (rs.next()) {
                String oID = rs.getString(1), 
                       dateCreate = rs.getString(2),
                        cname = rs.getString(3),
                        
                        cphone=rs.getString(4),
                        cAddress=rs.getString(5);
                double total = rs.getDouble(6);
                int status=rs.getInt(7),
                        cid =rs.getInt(8);
            Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                 System.out.println(bill);
             }
         } catch (SQLException ex) {
             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void getBillDetailByBillID(String BillID) {
        String query = "select * from Bill"
                + "where oid = '"+BillID+"'";
        ResultSet rs=dbconn.getData(query);
        
            
        try {
             while (rs.next()) {
                String oID = rs.getString(1), 
                       dateCreate = rs.getString(2),
                        cname = rs.getString(3),
                        
                        cphone=rs.getString(4),
                        cAddress=rs.getString(5);
                double total = rs.getDouble(6);
                int status=rs.getInt(7),
                        cid =rs.getInt(8);
            Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid);
                 System.out.println(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     public ArrayList<Bill> list() {
        ArrayList<Bill> arr= new ArrayList<Bill>();
        ResultSet rs = dbconn.getData("select*from Bill");
        try {
            while(rs.next()){

                  Bill bill = new Bill(rs.getString("oID"),rs.getString(2), rs.getString("cname"),
                          rs.getString("cphone"), rs.getString("cAddress"), 
                          rs.getDouble("total"), rs.getInt("status"), rs.getInt("cid"));
                  
                 System.out.println(bill);
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public ArrayList<BillDetail> listBD(String billId) {
        ArrayList<BillDetail> arrBD = new ArrayList<>();
        ResultSet rs = dbconn.getData("select*from BillDetail where oID ='"+billId+"'");
        try {
            while(rs.next()){

                  BillDetail billDT = new BillDetail(rs.getString("pID"),rs.getString(2), 
                          rs.getInt(3),rs.getDouble(4),rs.getDouble(5));
                  
                 System.out.println(billDT);
                arrBD.add(billDT);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrBD;
    }
    
    
// add a new bill
    public void addNewBill(String oID, String cname,String cphone,String cAddress,
    double total, int status, int cid){
        String preSql = "insert into Bill(oID,cname,cphone,cAddress,total,[status],cid)" +
"values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, oID);
            
            pre.setString(2, cname);
            pre.setString(3, cphone);
            pre.setString(4, cAddress);
            pre.setDouble(5, total);
            pre.setInt(6, status);
            
            pre.setInt(7, cid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getLastoID(){
        String result="nah";
        ResultSet rs = dbconn.getData("select*from Bill");
        try {
            while(rs.next()){

                
                  
                 result=rs.getString("oID");
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }
    
    public String makeOID(int cid){
        String oID= ""+cid;
        String result="";
        ResultSet rs = dbconn.getData("select*from Bill where cid="+cid);
        try {
            while(rs.next()){

                 result=rs.getString("oID");
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        oID=oID+result+1;
        //System.out.println(oID);
        return oID;
    }
    public Bill getBillByOID(String oid) {
        Bill bill = new Bill();
       ResultSet rs = dbconn.getData("select*from Bill where oid='"+oid+"'");
        try {
            while(rs.next()){

                  bill = new Bill(rs.getString("oID"),rs.getString(2), rs.getString("cname"),
                          rs.getString("cphone"), rs.getString("cAddress"), 
                          rs.getDouble("total"), rs.getInt("status"), rs.getInt("cid"));
                  
                 System.out.println(bill);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill;
    }
    
    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOBill dao = new DAOBill(dbconn);
        //dao.getBillByOID("1b11");
       // dao.makeOID(4);
       // dao.addNewBill("001", "ex", "000", "aa", 0, 1, 4);
        //dao.changeTotal("b4", 0);
        //dao.changeTotal("b2", 0);
       //dao.listBD("b2");
        //int n = dao.addBill(new Bill("b6", "Nguyen Van E", "943823", "34324",34.4,1,2));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
    }
}
