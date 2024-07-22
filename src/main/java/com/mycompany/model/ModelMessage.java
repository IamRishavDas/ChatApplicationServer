package com.mycompany.model;

public class ModelMessage {
    private boolean action;
    private String message;

    public ModelMessage(){}

    public ModelMessage(boolean aciton, String message){
        this.action = aciton;
        this.message = message;
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
}
