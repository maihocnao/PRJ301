/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Cart1;
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
public class DAOCart {
    Connection conn = null;
     DBConnect dbconn=null;
     PreparedStatement ps;
     ResultSet rx;
     
     public DAOCart(DBConnect dbconn){
         conn= dbconn.con;
         this.dbconn=dbconn;
     }
     
     public void addCart(String pid, int quantity){
         String query = "insert into Cart1(pid, quantity) values ('"+pid+"',"+ quantity +")";
        try {
            Statement  state = conn.createStatement();
            state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void updateQuantity(String pid, int quantity) {
         String query="  update Cart1 set quantity = "+ quantity +" where pid='"+pid+"'";
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
     }
     
     public boolean checkProductExistInCart(String pid){
         String query="select * from Cart1 where pid='"+pid+"'";
         ResultSet rs = dbconn.getData(query);
         boolean check = false;
        try {
            while (rs.next()){
                check = true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(check);
         return check;
     }
     
     public int returnQuantity(String pid) {
          String query="select * from Cart1 where pid='"+pid+"'";
         ResultSet rs = dbconn.getData(query);
         
         int quant =0;
        try {
            while (rs.next()){
                
                quant = rs.getInt(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(quant);
         return quant;
     }
     
     public void deleteByProduct(String pid) {
         String query = "delete from Cart1 where pid='"+pid+"'";
        try {
            
            Statement state = conn.createStatement();
            state.executeUpdate(query);
                    } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void deleteAll() {
           String query = "delete from Cart1";
        try {
          
            Statement state = conn.createStatement();
            state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     
     public ArrayList<Cart1> cartList() {
         ArrayList<Cart1> arr = new ArrayList<>();
         String query= "select * from Cart1";
         ResultSet rs = dbconn.getData(query);
        try {
            while(rs.next()){
                
                int cartID = rs.getInt(1);
                String pid = rs.getString(2);
                int quantity = rs.getInt(3);
                
                Cart1 cart = new Cart1(cartID, pid, quantity);
                arr.add(cart);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
         //System.out.println(arr);
         return arr;
     }
             
     
     
     public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOCart dao = new DAOCart(dbconn);
        //dao.returnQuantity("a1");
        //dao.checkProductExistInCart("a3");
       // dao.cartList();
        //dao.deleteAll();
       //dao.addCart("a1", 2);
      //dao.updateQuantity("a1", 0);
       //dao.deleteByProduct("a2");
        
    }
}
