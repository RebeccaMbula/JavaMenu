package dbHelperTest;

import beans.Food;
import dbHelper.DBHandler;
import javafx.collections.ObservableList;

public class DBHandlerTest {

    public static void main(String[] args){
        ObservableList<Food> foods = DBHandler.getFood("Breakfast", "Bakery");
        for(Food f : foods)
            System.out.println(f.getFoodName());
    }
}
