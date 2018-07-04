/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarestaurant;

import java.net.URL;
import java.util.ResourceBundle;

import beans.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Rebecca
 */
public class MenuController implements Initializable {

    @FXML ListView lvBreakfast;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList foods = FXCollections.observableArrayList();
        foods.add(new Food("Spaghetti Bolognese", "It is very tasty", 1300));
        foods.add(new Food("Chicken Curry", "It is very tasty... also.", 1300));
        foods.add(new Food("Spaghetti Bolognese", "It is very tasty", 130));
        foods.add(new Food("Egg Salad", "It is very tasty", 13));

        lvBreakfast.setItems(foods);
        lvBreakfast.setCellFactory(param -> new BreakfastListCell());
    }    
    
}
