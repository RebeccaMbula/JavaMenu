package javarestaurant;

import beans.Food;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public class DialogHelper {

    public static String showConfirmOrderDialogAndWait(ObservableList<Food> order){
        Dialog<String> dialog = new Dialog<>();

        GridPane grid = new GridPane();

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(60);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(40);
        grid.getRowConstraints().addAll(row1, row2);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        ColumnConstraints col2 = new ColumnConstraints(300);
        col2.setPercentWidth(80);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(10);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        VBox confirmOrderBox = new VBox();
        Label confirmOrderLabel = new Label("Confirm Order");
        TextArea orderList = new TextArea(getOrderString(order));
        orderList.setEditable(false);
        orderList.setPrefRowCount(7);
        orderList.setPrefColumnCount(50);
        confirmOrderBox.getChildren().addAll(confirmOrderLabel, orderList);

        VBox specificationsBox = new VBox();
        Label addSpecsLabel = new Label("Add Specifications");
        TextArea specsArea = new TextArea();
        specsArea.setPrefRowCount(2);
        specsArea.setPrefColumnCount(50);
        specificationsBox.getChildren().addAll(addSpecsLabel, specsArea);

        grid.add(confirmOrderBox, 1, 0);
        grid.add(specificationsBox, 1, 1);

        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(specsArea::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == confirmButtonType)
                return specsArea.getText();
            return null;
        });

        Optional<String> specifications = dialog.showAndWait();
        return specifications.orElse(null);
    }

    private static String getOrderString(ObservableList<Food> order) {
        StringBuilder orderString = new StringBuilder();
        for(Food food : order){
            orderString.append(lineFormat(food));
        }
        System.out.println(orderString.toString());
        return orderString.toString();
    }

    private static String lineFormat(Food f) {
        return f.getFoodName() + "\tKsh. " + f.getPrice() + "\n";
    }
}
