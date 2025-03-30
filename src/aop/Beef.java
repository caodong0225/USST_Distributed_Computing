package aop;

public class Beef implements Food{
    @Override
    public void eat() {
        System.out.println("Beef is eating...");
    }
}
