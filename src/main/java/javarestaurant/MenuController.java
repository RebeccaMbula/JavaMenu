/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarestaurant;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import beans.Food;
import beans.Order;
import dbHelper.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import listeners.FoodCheckListener;
import org.controlsfx.control.GridView;
import socketComm.OrdersComm;

/**
 * FXML Controller class
 *
 * @author Rebecca
 */
public class MenuController implements Initializable, FoodCheckListener {

    @FXML ListView lvChosenFood;
    @FXML ListView lvBreakfast;
    @FXML GridView hotDrinksGrid;
    @FXML GridView steaksGrid;
    @FXML Label lbPrice;

    ObservableList<Food> chosenFoods = FXCollections.observableArrayList();
    float price = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList foods = FXCollections.observableArrayList();
        foods.add(new Food("Spaghetti Bolognese", "It is very tasty", 1300));
        foods.add(new Food("Chicken Curry", "It is very tasty... also.", 1300));
        foods.add(new Food("Spaghetti Bolognese", "It is very tasty", 130));
        foods.add(new Food("Egg Salad", "It is very tasty", 13));

        ObservableList<Food> breakfast = DBHandler.getFood("Breakfast");

        lvBreakfast.setItems(breakfast);
        lvBreakfast.setCellFactory(param -> new FoodCell(this));

        lvChosenFood.setItems(chosenFoods);
        lvChosenFood.setCellFactory(param -> new ChosenFoodCell());
    }

    @Override
    public void onFoodCheck(Food food, boolean checked) {
        System.out.println(food.getFoodName() + " added");
        if (checked){
            chosenFoods.add(food);
            price += food.getPrice();
        }
        else{
            chosenFoods.remove(food);
            price -= food.getPrice();
        }
        lbPrice.setText("Ksh. " + price);
    }

    public void onClickOrder(ActionEvent e){
        String specifications = DialogHelper.showConfirmOrderDialogAndWait(chosenFoods).trim();
        //Send
        List<Food> foods = new ArrayList<>(chosenFoods);
        OrdersComm.sendOrder(new Order(foods, 10, specifications));
    }
}