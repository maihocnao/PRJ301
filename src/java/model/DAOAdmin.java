/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Admin;
import entity.Category;
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
public class DAOAdmin {
     Connection conn = null;
     DBConnect dbconn=null;
     PreparedStatement ps;
     ResultSet rx;

    public DAOAdmin(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbconn=dbconn;
    }
    
    public int addAdmin(Admin ad) {
        int n = 0;
        String preSql = "insert into admin(username,password) "
                + "values (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
             //? has index start 1
//            pre.setDataType(index of ?, value);
//            DataType id data of ?
            pre.setString(1, ad.getUserName());
            pre.setString(2, ad.getPassowrd());
             n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return n;
        
    }
    
    public int updateAdmin(Admin ad) {
        int n=0;
        String preSql = "update admin set username=?, password=?"
                + " where adminID=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, ad.getUserName());
            pre.setString(2, ad.getPassowrd());
            pre.setInt(3, ad.getAdminID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
        
    }
    
     public int changePassword(int adminId,String username,
            String oldpass,String newPass){
        int n=0;
        // check pass and repass -- javascript
        // check account (username, oldpass)
         String checksql="select * from Admin where username='"+username+"'"+
                " and password='"+oldpass+"'";
        ResultSet rs=dbconn.getData(checksql);
        try {
            if(!rs.next()){
                System.out.println("account don't exist");
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        // check OK
        //String sql="update Admin set password=? where cid=?";
        String preSql = "update admin set password =? where adminID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1,newPass);
            pre.setInt(2, adminId);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
     public void loginAdmin(String user, String pass) {
        String query = "select * from account\n"
                + "where [username] = '"+user+"'"
                + "and password = '"+pass+"'";
        ResultSet rs = dbconn.getData(query);
         try {
             while (rs.next()) {
                 int adminID = rs.getInt(1);
                 String userName= rs.getString(2);
                 String passowrd = rs.getString(3);
                 Admin ad = new Admin(adminID, userName, passowrd);
                 System.out.println(ad);
             }
         } catch (SQLException ex) {
             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     public void dislayAllAdmin() {
        String sql = "select * from Admin";
         ResultSet rs = dbconn.getData(sql);
        try {
          
                while (rs.next()) {
                 int adminID = rs.getInt(1);
                 String userName= rs.getString(2);
                 String passowrd = rs.getString(3);
                 Admin ad = new Admin(adminID, userName, passowrd);
                 System.out.println(ad);
                }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class
                    .getName()).log(Level.SEVERE, null, ex);
                }
    }
     
    public Admin login(String user, String pass) {
        String query = "select * from Admin where username = '"+user+"'"
                + " and password ="+pass+"'";
         ResultSet rs=dbconn.getData(query);
         
         try {
             while (rs.next()){
                 int adminID = rs.getInt(1);
                 String userName= rs.getString(2);
                 String passowrd = rs.getString(3);
                 Admin ad = new Admin(adminID, userName, passowrd);
                 System.out.println(ad);
             }
                
         } catch (SQLException ex) {
             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
          return null;
    }
     public int updateAdminWithpass(int adminID, String userName, String passWord) throws SQLException {
        int n=0;
        
        String preSql="update admin set username='"+userName+"',"+
            
                "password='"+ passWord +"'"
                + "where adminID="+adminID;
                
       try {
        PreparedStatement ps = conn.prepareStatement(preSql);
        n=ps.executeUpdate();
        ps.close();
        conn.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
     public int updateAdminWithoutPass(int adminID, String userName) throws SQLException {
        int n=0;
        
        String preSql="update admin set username='"+userName+"'"
            
               
                + "where adminID="+adminID;
                
       try {
        PreparedStatement ps = conn.prepareStatement(preSql);
        n=ps.executeUpdate();
        ps.close();
        conn.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public ArrayList<Admin> listAdmin() {
        ArrayList<Admin> arr= new ArrayList<Admin>();
        ResultSet rs = dbconn.getData("select*from Admin");
        try {
            while(rs.next()){
                Admin ad = new Admin(rs.getInt(1), 
                        rs.getString(2),rs.getString(3));
                arr.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
     
    public  void deleteAdmin(int id) {
        String query ="delete from admin where adminID =?" ;
         try {
             PreparedStatement pre =  conn.prepareStatement(query);
             pre.setInt(1, id);
             pre.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public boolean checkAdminExist(String un, String pass){
        boolean check = false;
         try {
             String query = "select * from admin where username ='"+un+"'and password='"+pass+"'";
             ResultSet rs = dbconn.getData(query);
             while(rs.next()) {
                 check = true;
                  Admin ad = new Admin(rs.getInt(1), 
                        rs.getString(2),rs.getString(3));
                 
             }} catch (SQLException ex) {
             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
        return check;
    }
    
     public static void main(String[] args) throws SQLException {
        DBConnect dbconn = new DBConnect();
        //add
        DAOAdmin dao = new DAOAdmin(dbconn);
       boolean a = dao.checkAdminExist("abc3", "12345678");
         System.out.println(a);
        //dao.deleteAdmin(9);
        //dao.updateAdminWithoutPass(1, "motconga");
       //int n = dao.updateAdminWithpass(1,"abc121111","0343299454");
        dao.listAdmin();
      //  dao.addAdmin(new Admin("johnnyd", "12345678"));
      
    }
}
