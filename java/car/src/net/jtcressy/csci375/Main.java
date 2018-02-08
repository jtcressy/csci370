package net.jtcressy.csci375;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.fillTank(13);
        System.out.println("Engine Started, running until fuel is gone...");
        car.startEngine();
    }
}
