package com.mycompany.model;

public class ModelMessage {
    private boolean action;
    private String message;
    private Object data;

    public ModelMessage(){}

    public ModelMessage(boolean aciton, String message, Object data){
        this.action = aciton;
        this.message = message;
        this.data = data;
    }

    public boolean isAction() {
        return action;
    }
    public void setAction(boolean action) {
        this.action = action;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData(){
        return this.data;
    }
    public void setData(Object data){
        this.data = data;
    }
}
