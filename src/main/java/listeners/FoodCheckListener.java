package listeners;

import beans.Food;

public interface FoodCheckListener {

    void onFoodCheck(Food food, boolean checked);
}
