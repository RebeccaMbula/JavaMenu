package javarestaurant;

import beans.Food;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import listeners.FoodCheckListener;

import java.io.IOException;

public class FoodCell extends ListCell<Food>{

    private MenuController menuController;

    public FoodCell() {
        super();
    }

    public FoodCell(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    protected void updateItem(Food item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            try {
                GridPane cell = FXMLLoader.load(getClass().getResource("/fxml/foodCell.fxml"));
                setCellContent(cell, item);
                setGraphic(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCellContent(Pane cell, Food item) {
        for (Node n : cell.getChildren()) {
            try{
                setCellContent(((Pane)n), item);
            } catch (Exception e){/*Do nothing*/}

            if(n.getId() != null && n.getId().equals("lbFoodName")){
                ((Label)n).setText(item.getFoodName());
            } else if(n.getId() != null && n.getId().equals("lbFoodDescription")) {
                ((Label) n).setText(item.getDescription());
            } else if(n.getId() != null && n.getId().equals("lbFoodPrice")){
                ((Label) n).setText("Ksh." + item.getPrice());
            } else if(n.getId() != null && n.getId().equals("foodCheck")){
                ((CheckBox)n).selectedProperty().addListener(((observable, oldValue, newValue) -> {
                    System.out.println(newValue ? "Has been checked" : "Unchecked");
                    FoodCheckListener foodCheckListener = menuController;
                    foodCheckListener.onFoodCheck(item, newValue);
                }));
            }
        }
    }
}