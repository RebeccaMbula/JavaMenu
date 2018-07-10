package layouts;

import beans.Food;
import beans.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import listeners.OrderDoneListener;

import java.io.IOException;
import java.util.List;

public class OrderCell extends ListCell<Order> {

    OrdersController controller;

    public OrderCell() {
    }

    public OrderCell(OrdersController controller) {
        this.controller = controller;
    }

    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        if(empty)
            setGraphic(null);
        else{
            try {
                GridPane cell = FXMLLoader.load(getClass().getResource("orderCell.fxml"));
                setCellContent(cell, item);
                setGraphic(cell);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void setCellContent(Pane cell, Order item) {
        for (Node n : cell.getChildren()) {
            if(n instanceof Pane)
                setCellContent(((Pane)n), item);

            if(n.getId() != null && n.getId().equals("lbOrder")){
                ((Label)n).setText(getOrderString(item.getFoods()));
            } else if(n.getId() != null && n.getId().equals("lbSpecifications")){
                ((TextArea)n).setText(item.getSpecifications());
            } else if(n.getId() != null && n.getId().equals("btnDone")){
                ((Button)n).setOnAction(event -> {
                    OrderDoneListener orderDoneListener = (OrderDoneListener) controller;
                    orderDoneListener.onOrderDone(item);
                });
            }
        }
    }

    private String getOrderString(List<Food> foods){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < foods.size(); i++){
            stringBuilder.append((i+1) + ". " + foods.get(i).getFoodName() + ",  ");
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
