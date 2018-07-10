package dbHelper;

import beans.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBHandler {
    private static final String URL = "jdbc:sqlite:MenuApp.db";

    private static Connection connection;

    private static void connect(){
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Food> getFood(String mealType, String cartegory) {
        if(connection == null)
            connect();

        ArrayList<String> params = new ArrayList<>(2);
        params.addAll(Arrays.asList(mealType, cartegory));
        String sql = "SELECT * FROM food WHERE `types`=? AND category=?";


        if (mealType == null) {
            params.remove(0);
            sql = "SELECT * FROM food WHERE category=?";
        } else if(cartegory == null){
            params.remove(1);
            sql = "SELECT * FROM food WHERE `types`=?";
        }

        PreparedStatement prpStmt = null;
        ObservableList<Food> foods = FXCollections.observableArrayList();
        try {
            prpStmt = connection.prepareStatement(sql);
            for(int i = 0; i < params.size(); i++){
                prpStmt.setString(i+1, params.get(i));
            }
            ResultSet resultSet = prpStmt.executeQuery();
            foods = resultSetToList(resultSet);
        } catch (SQLException e){
            System.out.println(prpStmt.toString());
            e.printStackTrace();
        }
        return foods;
    }

    public static ObservableList<Food> getFood(String mealType) {
        return getFood(mealType, null);
    }

    public static ObservableList<Food> getFoodByCartegory(String cartegory){
        return getFood(null, cartegory);
    }


    private static ObservableList<Food> resultSetToList(ResultSet rs) throws SQLException {
        ObservableList<Food> foods = FXCollections.observableArrayList();
        while(rs.next()){
            foods.add(new Food(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price")
            ));
        }
        return foods;
    }
}
