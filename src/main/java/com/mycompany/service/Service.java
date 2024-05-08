
package com.mycompany.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.mycompany.model.ModelRegister;
import javax.swing.JTextArea;


public class Service {
    private static Service service;
    private SocketIOServer server;
    private final int PORT_NUMBER = 6969;
    private JTextArea textArea;
    
    public static Service getService(JTextArea textArea){
        if(service == null){
            service = new Service(textArea);
        }
        return service;
    }
    private Service(JTextArea textArea){
        this.textArea = textArea;
    }
    
    public void startServer(){
        Configuration configuration = new Configuration();
        configuration.setPort(PORT_NUMBER);
        server = new SocketIOServer(configuration);
        server.addConnectListener((SocketIOClient sioc) -> {
            textArea.append("One client connected! \n");
        });
        server.addEventListener("register", ModelRegister.class, new DataListener<ModelRegister>() {
            @Override
            public void onData(SocketIOClient sioc, ModelRegister t, AckRequest ar) throws Exception {
                System.out.println("register event invoked!!");
                textArea.append("User has Register :" + t.getUsername()+ " Pass :" + t.getPassword() + "\n");
            }
        });
        server.start();
        textArea.append("The server has started on Port No: " + PORT_NUMBER + "\n");
    }
}
