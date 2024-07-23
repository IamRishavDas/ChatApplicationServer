package com.mycompany.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.connnections.DatabaseConnection;
import com.mycompany.model.ModelMessage;
import com.mycompany.model.ModelRegister;
import com.mycompany.model.ModelUserAccount;

public class ServiceUser {
    public ServiceUser() {
        this.connection = DatabaseConnection.getDatabaseConnection().getConnection();
    }

    private final Connection connection;

    private final String SELECT_USER_ACCOUNT = "select userId, userName, gender, imageString from user_account where user_account.status = '1' and userId <> ?";
    private final String INSERT_USER = "insert into user (userName, password) values (?, ?)";
    private final String INSERT_USER_ACCOUNT = "insert into user_account (userId, userName) values (?, ?)";
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
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS); // insert into register
                preparedStatement.setString(1, data.getUsername());
                preparedStatement.setString(2, data.getPassword());
                preparedStatement.execute();

                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.first();
                int userId = resultSet.getInt(1);
                resultSet.close();
                preparedStatement.close();

                preparedStatement = connection.prepareStatement(INSERT_USER_ACCOUNT); // creating user account
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, data.getUsername());
                preparedStatement.execute();
                preparedStatement.close();
                connection.commit();
                connection.setAutoCommit(true);
                modelMessage.setAction(true);
                modelMessage.setMessage("Ok");
                modelMessage.setData(new ModelUserAccount(userId, data.getUsername(), "", "", true));
            }
        } catch (SQLException e) {
            System.out.println(e);
            modelMessage.setAction(false);
            modelMessage.setMessage("Server Error");
            try {
                if(!connection.getAutoCommit()){
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                System.out.println(e);
            }
        }
        return modelMessage;
    }

    public List<ModelUserAccount> getUser(int exitUser) throws SQLException{
        List<ModelUserAccount> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ACCOUNT);
        preparedStatement.setInt(1, exitUser);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int userId = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String gender = resultSet.getString(3);
            String image = resultSet.getString(4);
            list.add(new ModelUserAccount(userId, userName, gender, image, true));
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }
}
