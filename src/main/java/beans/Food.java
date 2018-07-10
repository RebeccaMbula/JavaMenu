//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package beans;

import java.io.Serializable;

public class Food implements Serializable {
    String foodName;
    String description;
    float price;
    private static final long serialVersionUID = -7667177779774398221L;

    public Food() {
    }

    public Food(String var1, String var2, float var3) {
        this.foodName = var1;
        this.description = var2;
        this.price = var3;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public void setFoodName(String var1) {
        this.foodName = var1;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String var1) {
        this.description = var1;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float var1) {
        this.price = var1;
    }
}
