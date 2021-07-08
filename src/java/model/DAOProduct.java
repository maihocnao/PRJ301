/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customer;
import entity.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vt88
 */
public class DAOProduct {
    Connection conn = null;
    DBConnect dbConn=null;
    PreparedStatement ps= null;

    public DAOProduct(DBConnect dbconn) {
       conn = dbconn.con;
        this.dbConn=dbconn;
         
    }
    public int addProduct(Product pro){
        int n = 0;
        String preSql = "insert into Product(pid,pname,quantity,price,[image],[description],[status],cateID) \n" +
"values (?,?,?,?,?,?,?,?)";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(preSql);
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateID());
            n = pre.executeUpdate();
           // System.out.println("inserted");
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return n;
    }
    
    public String getPName(String pid){
        String pname = "";
        String query = "select * from product\n"
                + "where pid = '"+pid+"'";
        ResultSet rs=dbConn.getData(query);
        
            
        try {
            while (rs.next()) {
               // String pid=rs.getString("pid");//rs.getString(1)
                 pname=rs.getString(2);//rs.getString("pname");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(pname);
        return pname;
    }
    
    public Double getPPrice(String pid){
        double price = 0;
        String query = "select * from product\n"
                + "where pid = '"+pid+"'";
        ResultSet rs=dbConn.getData(query);
        
            
        try {
            while (rs.next()) {
                       price=rs.getDouble("price");        
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(price);
        return price;
    }
    
    
    
    public int updateProduct(Product pro){
        int n=0;
        String preSql="update Product set pname=?"+
                ",quantity=?"+","
                + "price=?,image=?,description=?"+","
                + "status=?,cateID=?"+" "
                + "where pid=?";
        
        try {
            PreparedStatement pre=conn.prepareStatement(preSql);
            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setString(5, pro.getDescription());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateID());
            pre.setString(8,pro.getPid());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateQuantity(String pid,int quantity){
        int n=0;
        
      
//        String sql="update Product set "
//                + "quantity=quantity + "+quantity+"  "
//                + "where pid='"+pid+"'";
//        try {
//            Statement state=conn.createStatement();
//            n=state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }

            String preSql="update Product set quantity=? where pid=?";
        try {
            PreparedStatement pre= conn.prepareCall(preSql);
            pre.setInt(1, quantity);
            pre.setString(2, pid);
            n =pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return n;
    }
   
   
      public void dislayAllProduct() {
        String sql = "select * from Product";
        try {
           
            ResultSet rs = dbConn.getData(sql);
            
            while (rs.next()) {
                
                String pid=rs.getString("pid");//rs.getString(1)
                String  pname=rs.getString(2);//rs.getString("pname");
                int quantity=rs.getInt(3);//rs.getInt("quantity")
                double price=rs.getDouble("price");
                String image=rs.getString(5),
                        description=rs.getString(6);
                int status=rs.getInt(7), 
                        cateID=rs.getInt(8);
                Product pro=new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);
           }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      
public Product getProductByID(String productid) {
    
        String query = "select * from product\n"
                + "where pid = '"+productid+"'";
        ResultSet rs=dbConn.getData(query);
        Product pro = new Product();
        
            
        try {
            while (rs.next()) {
                String pid=rs.getString("pid");//rs.getString(1)
                String  pname=rs.getString(2);//rs.getString("pname");
                int quantity=rs.getInt(3);//rs.getInt("quantity")
                double price=rs.getDouble("price");
                String image=rs.getString(5),
                        description=rs.getString(6);
                int status=rs.getInt(7), 
                        cateID=rs.getInt(8);
                
                 pro=new Product(pid, pname, quantity, price, image, description, status, cateID);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pro;
    }

public void  searchProductByName(String nameSearch) {
        
        String query = "select * from product\n"
                + "where [name] like '"+nameSearch+"'";
        ResultSet rs=dbConn.getData(query);
        
            
        try {
            while (rs.next()) {
                String pid=rs.getString("pid");//rs.getString(1)
                String  pname=rs.getString(2);//rs.getString("pname");
                int quantity=rs.getInt(3);//rs.getInt("quantity")
                double price=rs.getDouble("price");
                String image=rs.getString(5),
                        description=rs.getString(6);
                int status=rs.getInt(7), 
                        cateID=rs.getInt(8);
                
                Product pro=new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void displayProductQuantity(int quan){
        String sql="select pname, quantity "
                + "from product where quantity>"+quan;
        ResultSet rs=dbConn.getData(sql);
        try {
            while(rs.next()){
                System.out.println("Name:"+rs.getString(1)+
                        "\t quantity:"+rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void getTop3() {
      
        String query = "select top 3 * from product";
        ResultSet rs=dbConn.getData(query);
        try {
           
            rs = ps.executeQuery();
            while (rs.next()) {
                 String pid=rs.getString("pid");//rs.getString(1)
                String  pname=rs.getString(2);//rs.getString("pname");
                int quantity=rs.getInt(3);//rs.getInt("quantity")
                double price=rs.getDouble("price");
                String image=rs.getString(5),
                        description=rs.getString(6);
                int status=rs.getInt(7), 
                        cateID=rs.getInt(8);
                Product pro=new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);
            }
        } catch (Exception e) {
        }
       
    }
    public int deleteProduct(String pid) {
        int n=0;
        String query ="delete from product where pid=?";
       try {
            PreparedStatement pre=conn.prepareStatement(query);
            pre.setString(1, pid);
           // pre.setInt(2, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
    public ArrayList<Product> list() {
        ArrayList<Product> arr = new ArrayList<>();
        ResultSet rs = dbConn.getData("select * from Product");
        try {
            while(rs.next()) {
                 String pid=rs.getString("pid");//rs.getString(1)
                String  pname=rs.getString(2);//rs.getString("pname");
                int quantity=rs.getInt(3);//rs.getInt("quantity")
                double price=rs.getDouble("price");
                String image=rs.getString(5),
                        description=rs.getString(6);
                int status=rs.getInt(7), 
                        cateID=rs.getInt(8);
                Product pro=new Product(pid, pname, quantity, price, image, description, status, cateID);
                //System.out.println(pro);
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    
//    public void updateProductByID()
//    {
////        String query = "update Product set pname='"+pName+"',quantity="+qtity+",price ="+pprice+",\n" +
////"[image]='"+pimage+"',[description]='"+ descrip+"',[status]="+ pstatus+",cateID="+ pcateID+" where pid='"+pID+"'";
////         String query = "update Product set pname='haha',quantity=7,price =7,\n" +
////"[image]='anh',[description]='tinhtrang',[status]=1,cateID=1 where pid='a1'";
//        String query ="update Product set pname='abc',quantity=10,price =122300,\n" +
//"[image]='image1',[description]='good',[status]=1,cateID=3 where pid='a1'";
//        ResultSet rs=dbConn.getData(query);
//        
//            
//        try {
//            while (rs.next()) {
//                String pid=rs.getString("pid");//rs.getString(1)
//                String  pname=rs.getString(2);//rs.getString("pname");
//                int quantity=rs.getInt(3);//rs.getInt("quantity")
//                double price=rs.getDouble("price");
//                String image=rs.getString(5),
//                        description=rs.getString(6);
//                int status=rs.getInt(7), 
//                        cateID=rs.getInt(8);
//                
//                Product pro=new Product(pid, pname, quantity, price, image, description, status, cateID);
//                System.out.println(pro);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//    }
    public int updateProduct(String pid, String pname, int quantity, double price, String image, String description, int status, int cateID) {
        int n = 0;
        String sql = "update Product set pname=?,quantity=?,price =?,\n" +
"[image]=?,[description]=?,[status]=?,cateID=? where pid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pname);
            pre.setInt(2, quantity);
            pre.setDouble(3, price);
            pre.setString(4, image);
            pre.setString(5, description);
            pre.setInt(6, status);
            pre.setInt(7, cateID);
            pre.setString(8,pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DBConnect dbcon=new DBConnect();
        DAOProduct dao=new DAOProduct(dbcon);
        //dao.getPPrice("a4");
      // dao.updateProduct("a4","Laptop asus zenbook", 3, 150000, "https://bitly.com.vn/0sz97c", "Laptop Asus Vivobook D515UA-EJ045T/ 2 Yrs", 0, 3);
      // dao.getPName("a4");
        //dao.list();
        //dao.getTop3();
       //dao.getProductByID();
       //dao.addProduct(new Product("b4","Laptop asus", 3, 15000000, "https://cdn.cellphones.com.vn/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/l/a/laptop-asus-zenbook-13-ux325ea-9.jpg", "Laptop Asus Vivobook D515UA-EJ045T/ 2 Yrs", 1, 3));
       // dao.dislayAllProduct();
       //dao.displayProductQuantity(11);
      //dao.deleteProduct("19");
    }

    
}
