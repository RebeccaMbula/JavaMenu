package javarestaurant;

import beans.Food;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BreakfastListCell extends ListCell<Food> {

    @FXML ImageView foodImage;
    @FXML Label lbFoodName;
    @FXML Label lbFoodDescription;
    @FXML CheckBox foodCheck;

    @Override
    protected void updateItem(Food item, boolean empty) {
        super.updateItem(item, empty);
        if(empty) {
            setGraphic(null);
        }
        else {
            try {
                GridPane breakfastCell = FXMLLoader.load(getClass().getResource("breakfastCell.fxml"));
                /*for (Node n : breakfastCell.getChildren()){
                    if(n.getId() != null && n.getId().equals("lbFoodName")){
                        ((Label)n).setText("lala");
                        System.out.println("This is lbFoodName");
                    } else if(n == lbFoodDescription){
                        ((Label)n).setText(item.getDescription());
                    } else {
                    }
                }*/
                setCellContent(breakfastCell, item);
                setGraphic(breakfastCell);
            } catch(IOException e) {
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
                System.out.println("This is lbFoodName");
            } else if(n.getId() != null && n.getId().equals("lbFoodDescription")){
                ((Label)n).setText(item.getDescription());
            } else {
            }
        }
    }
}
