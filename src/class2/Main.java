package class2;
import java.util.Scanner;
/**
 * @author jyzxc
 */
public class Main {
    public static void main(String[] args){
        Agent agent = new Agent();
        System.out.println("Please input your choice(Car/Bike/Air): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        Vehicle vehicle = agent.getVehicle(choice);
        Person person = new Person(vehicle);
        person.goOutSide();
    }
}
