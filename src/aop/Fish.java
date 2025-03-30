package aop;

public class Fish implements Food{

    @Override
    public void eat() {
        System.out.println("Eating the fish");
    }
}
