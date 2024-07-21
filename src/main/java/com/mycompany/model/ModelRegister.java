
package com.mycompany.model;

import org.json.JSONException;
import org.json.JSONObject;


public class ModelRegister {
    private String username;
    private String password;

    public ModelRegister(){}
    
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
    
    @Override
    public String toString(){
        return "ModelRegister{" +
        "username='" + this.username + '\'' +
        ", password='" + this.password + '\'' +
        '}';
    }
    
    public JSONObject toJSONObject(){       
        try{
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);
            return json;
        }catch(JSONException ex){
            return null;
        }
    }
}
