package aop;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Scanner;

/**
 * @author jyzxc
 */
public class Main {
    public static void main(String args[])
    {
        RestaurantInterface restaurantService = new Restaurant();
        InvocationHandler invocationHandler = new ProxyHandler(restaurantService);
        RestaurantInterface restaurant = (RestaurantInterface) Proxy.newProxyInstance(
                restaurantService.getClass().getClassLoader(),
                restaurantService.getClass().getInterfaces(),
                invocationHandler
        );
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of your food");
        String foodName = sc.nextLine();
        sc.close();
        // 完成上菜
        Food food = restaurant.getFood(foodName);
        // 吃饭
        food.eat();
        System.out.println("Thank you for ordering, see you soon!");
    }
}
