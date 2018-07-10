//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package beans;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private List<Food> foods;
    private int tableNumber;
    private String specifications;
    private static final long serialVersionUID = -7667177779774398221L;

    public Order(List<Food> var1, int var2, String var3) {
        this.foods = var1;
        this.tableNumber = var2;
        this.specifications = var3;
    }

    public List<Food> getFoods() {
        return this.foods;
    }

    public void setFoods(List<Food> var1) {
        this.foods = var1;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int var1) {
        this.tableNumber = var1;
    }

    public String getSpecifications() {
        return this.specifications;
    }

    public void setSpecifications(String var1) {
        this.specifications = var1;
    }
}
