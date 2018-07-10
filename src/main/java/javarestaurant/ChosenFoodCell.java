package javarestaurant;

import beans.Food;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ChosenFoodCell extends ListCell<Food> {

    @Override
    protected void updateItem(Food item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            try {
                GridPane cell = FXMLLoader.load(getClass().getResource("/fxml/ChosenFoodCell.fxml"));
                setCellContent(cell, item);
                setGraphic(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCellContent(Pane cell, Food item) {
        for (Node n : cell.getChildren()) {
            if(n instanceof Pane)
                setCellContent(((Pane)n), item);

            if(n.getId() != null && n.getId().equals("lbFoodName")){
                ((Label)n).setText(item.getFoodName());
            } else if(n.getId() != null && n.getId().equals("lbPrice")){
                ((Label)n).setText("Ksh. " + item.getPrice());
            }
        }
    }
}
