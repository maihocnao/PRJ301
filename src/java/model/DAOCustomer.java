/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customer;
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
 * @author HP
 */
public class DAOCustomer {

    Connection conn = null;
    DBConnect dbConn=null;

    public DAOCustomer(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn=dbconn;
    }

    // insert, update, delete, select
    public int addCustomer(Customer cus) {
        int n = 0;
        String preSQL = "insert into Customer(cname,cphone,"
                + "cAddress,username,password,status)"
                + " values(?,?,?,?,?,?)";
        try {
            // ? has index start 1
            PreparedStatement pre = conn.prepareStatement(preSQL);
//            pre.setDataType(index of ?, value);
//            dataType is data of ?
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            // execute
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String sql="insert into Customer(cname,cphone,cAddress,username,password,status)"
//                + " values('"+cus.getCname()+"','"+cus.getCphone()+
//                "','"+cus.getcAddress()+"','"+cus.getUsername()+
//                "','"+cus.getPassword()+"',"+cus.getStatus()+")";
//        try {
//            Statement state=conn.createStatement();
//            n=state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }
    
//get a Customer by ID 
    public Customer getCusByID(int cusID){
        Customer cus = new Customer();
       
        String query = "select * from Customer where cid ="+cusID;
        ResultSet rs = dbConn.getData(query);
        try {
            while(rs.next()){
                int cid = rs.getInt(1);
               
                String cname=rs.getString(2);//rs.getString("cname");
                String cphone=rs.getString("cphone"),
                cAddress=rs.getString(4),
                        username=rs.getString(5), 
                        password=rs.getString(6);
                int status = rs.getInt(7);
                cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
                

        return  cus;
    }
    
     public Customer getCusByUserName(String uname){
        Customer cus = new Customer();
       
        String query = "select * from Customer where username ='"+uname+"'";
        ResultSet rs = dbConn.getData(query);
        try {
            while(rs.next()){
                int cid = rs.getInt(1);
               
                String cname=rs.getString(2);//rs.getString("cname");
                String cphone=rs.getString("cphone"),
                cAddress=rs.getString(4),
                        username=rs.getString(5), 
                        password=rs.getString(6);
                int status = rs.getInt(7);
                cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        // System.out.println(cus);
        return  cus;
    }
    
    public boolean isCustomer(String username,String pass){
        boolean flag=false;
        String sql="select* from Customer where username='"+username+"' and password='"+pass+"' ";
        ResultSet rs=dbConn.getData(sql);
        try {
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }System.out.println(flag);
        return flag;
    }
    
    
    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "update Customer set cname=?,cphone=?,"
                + "cAddress=?,username=?,password=?,status=?"
                + " where cid=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getCphone());
            pre.setString(3, cus.getcAddress());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            pre.setInt(6, cus.getStatus());
            pre.setInt(7, cus.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateCustomerWithouPassword(int id, String cname, String cphone,
            String caddress, String username, int status) {
        int n = 0;
        String sql = "update Customer set cname=?,cphone=?,"
                + "cAddress=?,username=?,status=?"
                + " where cid=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cname);
            pre.setString(2, cphone);
            pre.setString(3, caddress);
            pre.setString(4, username);
           // pre.setString(5, cus.getPassword());
            pre.setInt(5, status);
            pre.setInt(6, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changePassword(int id, String user,
            String oldPass, String newPass) {
        int n = 0;
        // check userName is true (user, oldPass)
        String sql = "update Customer set password=? where cid=? ";

        return n;
    }

    public int changeStatus(int id, int status) {
        int n = 0;
        return n;
    }
    public boolean login(String username,String pass){
        boolean flag=false;
        String sql="Select * from Customer"
                + " where username='"+username+"' "
                + "and password='"+pass+"'";
        ResultSet rs=dbConn.getData(sql);
        try {
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public void displayAllCustomer() {
        String sql = "select * from Customer";
        try {
            //TYPE_FORWARD_ONLY: default, con tro bản ghi top - down only
            // Statement state=conn.createStatement();
            //TYPE_SCROLL_SENSITIVE: thread safe
            //CONCUR_READ_ONLY: default no modify rs
            //CONCUR_UPDATABLE: modify rs
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
//            ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
//                rs.getDataType(index|fieldName);
//                DataType is DataType of fieldName
                int cid=rs.getInt("cid");//rs.getInt(2);
                String cname=rs.getString(2);//rs.getString("cname");
                String cphone=rs.getString("cphone"),
                cAddress=rs.getString(4),
                        username=rs.getString(5), 
                        password=rs.getString(6);
                int status = rs.getInt(7);
                Customer cus=new Customer(cid, cname, cphone, cAddress, username, password, status);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int deleteCustomer(int cID){
        int n=0;
        String query ="delete from Customer where cid=?";
       try {
            PreparedStatement pre=conn.prepareStatement(query);
           
            pre.setInt(1, cID);
           // pre.setInt(2, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
     public ArrayList<Customer> list() {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        ResultSet rs = dbConn.getData("select* from Customer");
        try {
            while (rs.next()) {
               int cid = rs.getInt("cid");//rs.getInt(2);
                String cname = rs.getString(2);//rs.getString("cname");
                String cphone = rs.getString("cphone"),
                        cAddress = rs.getString(4),
                        username = rs.getString(5),
                        password = rs.getString(6);
                int status = rs.getInt(7);
                Customer cus = new Customer(cid, cname, cphone, cAddress, username, password, status);
                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOCustomer dao = new DAOCustomer(dbconn);
        dao.getCusByUserName("ngva1");
        //dao.isCustomer("ngva1", "12345678");
       // dao.getCusByID(1);
        //dao.updateCustomerWithouPassword(1, "Nguyen A", "030943954","Trost", "ngvaaa", 1);
        //int n = dao.deleteCustomer(11);
     //int n = dao.addCustomer(new Customer("Jaehuyn", "093232", "hanoi", "hundg12d", "1234123@123", 1));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
           //dao.displayAllCustomer();
        //   dao.updateCustomerWithouPassword(1, "Nguyen A", "0000001", "address000", "ngva1", 0);
    }

}
