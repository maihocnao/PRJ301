/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author HP
 */
public class Admin {
    private int adminID;
    private String userName,passowrd;

    public Admin(int adminID, String userName, String passowrd) {
        this.adminID = adminID;
        this.userName = userName;
        this.passowrd = passowrd;
    }

    public Admin(String userName, String passowrd) {
        this.userName = userName;
        this.passowrd = passowrd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassowrd() {
        return passowrd;
    }

    @Override
    public String toString() {
        return "Admin{" + "adminID=" + adminID + ", userName=" + userName + ", passowrd=" + passowrd + '}';
    }
    
    
}
