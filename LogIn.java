package store;

import java.io.*;

public class LogIn{
    
    private String userName;
    private String password;
    private String status;
    private int point;
    
    //User is the abstract class. Allows to use methods from User class. 
    private User u; 
    
    //create method t set state for 2 types of users: Owners or Customers. 
    public void setState(User u){
        this.u = u;
    }
    
    //create method to set userName. 
    public void setName(String userName){
        this.userName=userName;
    }
    
    //method to get userName. 
    public String getName(){
        return userName;
    }
    
    //create method to set password. 
    public void setPassword(String password){
        this.password = password;
    }
    
    //method to get password.
    public String getPassword(){
        return password;
    }
}