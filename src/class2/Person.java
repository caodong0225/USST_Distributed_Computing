package class2;

/**
 * @author jyzxc
 */
public class Person {
    private Vehicle vehicle;
    Person(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void goOutSide() {
        vehicle.drive();
    }
}
