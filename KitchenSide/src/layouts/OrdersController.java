package layouts;

import beans.Food;
import beans.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import listeners.OrderDoneListener;
import server.KitchenServer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersController implements Initializable, KitchenServer.OrderListener, OrderDoneListener {

    @FXML ListView<Order> lvOrders;
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Mokimo", "Verryyy nice", 2000));
        foods.add(new Food("Mokimo", "Verr nice", 2000));
//        foods.add(new Food("Mokimo", "Verryy nice", 2000));
//        foods.add(new Food("Mokimo", "Verry nice", 2000));
//        foods.add(new Food("Mokimo", "Verryyy nice", 2000));
//        foods.add(new Food("Mokimo", "Verr nice", 2000));
//        foods.add(new Food("Mokimo", "Verryy nice", 2000));
//        foods.add(new Food("Mokimo", "Verry nice", 2000));
//        foods.add(new Food("Mokimo", "Verryyy nice", 2000));
//        foods.add(new Food("Mokimo", "Verr nice", 2000));
//        foods.add(new Food("Mokimo", "Verryy nice", 2000));
//        foods.add(new Food("Mokimo", "Verry nice", 2000));

        orders.add(new Order(foods,1, "ble\nb\nleb\nle"));
        orders.add(new Order(foods,2, "blebleble"));
        orders.add(new Order(foods, 3, "blebleble"));
        orders.add(new Order(foods,4, "blebleble"));
        System.out.println("Size of foods: " + foods.size());

        lvOrders.setItems(orders);
        lvOrders.setCellFactory(param -> new OrderCell(this));

    }

    @Override
    public void onReceiveOrder(Order o) {
        orders.add(o);
    }

    @Override
    public void onOrderDone(Order order) {
        orders.remove(order);
    }
}
