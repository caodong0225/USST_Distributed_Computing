package aop;

public class MyAspect {
    public void inspect(){
        System.out.println("Inspecting the temperature");
    }
    public void sendFruit(){
        System.out.println("Sending fruit to the customer");
    }
    public void pay(){
        System.out.println("Paying the bill");
    }
}
