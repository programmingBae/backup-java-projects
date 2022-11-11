package com.example.teori3.Controller;

import com.example.teori3.DAO.UserDAO;
import com.example.teori3.Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class MainController {
    public ListView listUsers;
    private UserDAO userDAO = new UserDAO();

    public void initialize(){
        ObservableList<User> users = (ObservableList<User>) userDAO.showData();
        listUsers.setItems(users);
        System.out.println(users);
    }

    public void add(ActionEvent actionEvent) {
        userDAO.addData(new User("tes"));
        ObservableList<User> users = (ObservableList<User>) userDAO.showData();
        listUsers.setItems(users);
    }
}
