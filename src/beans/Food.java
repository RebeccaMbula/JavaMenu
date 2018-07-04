package beans;

public class Food {

    String foodName;
    String description;
    float price;

    public Food() {}

    public Food(String foodName, String description, float price) {
        this.foodName = foodName;
        this.description = description;
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
