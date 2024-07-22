package com.mycompany.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.connnections.DatabaseConnection;
import com.mycompany.model.ModelMessage;
import com.mycompany.model.ModelRegister;

public class ServiceUser {
    public ServiceUser() {
        this.connection = DatabaseConnection.getDatabaseConnection().getConnection();
    }

    private final Connection connection;

    private final String INSERT_USER = "insert into user (userName, password) values (?, ?)";
    private final String CHECK_USER  = "select userId from user where userName = ? limit 1";

    public ModelMessage register(ModelRegister data) {
        ModelMessage modelMessage = new ModelMessage();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, data.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                modelMessage.setAction(false);
                modelMessage.setMessage("User Already Exists!");
            } else {
                modelMessage.setAction(true);
            }
            resultSet.close();
            preparedStatement.close();
            if (modelMessage.isAction()) {
                preparedStatement = connection.prepareStatement(INSERT_USER);
                preparedStatement.setString(1, data.getUsername());
                preparedStatement.setString(2, data.getPassword());
                preparedStatement.execute();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
            modelMessage.setAction(false);
            modelMessage.setMessage("Server Error");
        }
        return modelMessage;
    }
}
