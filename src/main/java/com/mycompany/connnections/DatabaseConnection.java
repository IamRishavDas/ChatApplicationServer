
package com.mycompany.connnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private Connection connection;
    private DatabaseConnection(){}
    private static DatabaseConnection databaseConnection;
    public static DatabaseConnection getDatabaseConnection(){
        if(databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }
    public void connectionToDataBase() throws SQLException{
        
        String server = "localhost";
        String port = "3306";
        String database = "chatapplication";
        String username = "root";
        String password = "rootp@$$?0RD";
        
        connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
