
package com.mycompany.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.mycompany.model.ModelMessage;
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
        System.out.println(
            "The line before the eventListener"
        );
        server.addEventListener("register", ModelRegister.class, new DataListener<ModelRegister>() {
            @Override
            public void onData(SocketIOClient sioc, ModelRegister registerData, AckRequest ar) throws Exception {
                ModelMessage modelMessage = new ServiceUser().register(registerData);
                ar.sendAckData(modelMessage.isAction(), modelMessage.getMessage());
                textArea.append("User has registered { userName: " + registerData.getUsername() + " Password: " + registerData.getPassword() + " }\n");
            }
        });
        server.start();
        textArea.append("The server has started on Port No: " + PORT_NUMBER + "\n");
    }
}
