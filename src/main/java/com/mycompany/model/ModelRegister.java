
package com.mycompany.model;


public class ModelRegister {
    private String username;
    private String password;
    
    public ModelRegister(String userName, String password){
        this.username = userName;
        this.password = password;
    }
       
    public void setUsername(String userName){
        this.username = userName;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
}
