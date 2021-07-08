/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
 * @author HP
 */
//DAO database access object
public class DAOCategory {
    Connection conn=null;
    DBConnect dbconn=null;
    public DAOCategory(DBConnect dbconn) {
        conn=dbconn.con;
        this.dbconn=dbconn;
    }
    //insert, update, delete -- return number of records
    public int addCategory(Category cate){
        int n=0;
        String sql="insert into Category(cateName, status) "
                + "values ('"+cate.getCateName()+
                "',"+cate.getStatus()+")";
        try {
            Statement state=conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public ArrayList<Category> listCate() {
        ArrayList<Category> arr= new ArrayList<Category>();
        ResultSet rs = dbconn.getData("select*from Category");
        try {
            while(rs.next()){
                Category cate = new Category(rs.getInt(1), 
                        rs.getString(2),rs.getInt(3));
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public void displayCateName(){
        
    }
    public int deleteCategory(int cateID){
        int n=0;
        String query ="  delete from category where cateID=?";
       try {
            PreparedStatement pre=conn.prepareStatement(query);
            pre.setInt(1, cateID);
           // pre.setInt(2, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateCategory(int cateID, String cateName, int status) throws SQLException {
        int n=0;
//        String query ="update Category set cateName=?, status=?"
//                + "where cateID=?";
        
        String preSql="update Category set cateName='"+cateName+"',"+
            
                "status="+status
                + "where cateID="+cateID;
                
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
    
    
    
    public static void main(String[] args) throws SQLException {
        DBConnect dbconn=new DBConnect();
        DAOCategory dao=new DAOCategory(dbconn);
        //dao.listCate();
      //  int n=dao.updateCategory(2, "mai", 1);
//        if(n>0)
//            System.out.println("inserted");
    }
    
}
