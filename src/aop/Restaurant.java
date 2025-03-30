package aop;

/**
 * @author jyzxc
 */
public class Restaurant implements RestaurantInterface{
    Kitchen kitchen = new Kitchen();

    @Override
    public Food getFood(String food) {
        return kitchen.getFood(food);
    }

}
