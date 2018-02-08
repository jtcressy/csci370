
package net.jtcressy.csci375;

public class Car {
    public enum CarState {
        ON, OFF, FULL, EMPTY
    }
    private static float MAXFUEL = 13;
    private CarState carstate;
    private CarState tankstate;
    private int speed;
    private float fuellevel;
    Car() {
        carstate = CarState.OFF;
        tankstate = CarState.EMPTY;
        speed = 0;
        fuellevel = 0;
    }

    public void startEngine() {
        if (carstate == CarState.OFF) {
            carstate = CarState.ON;
            try {
                runUntilEmpty(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopEngine() {
        if (speed > 0 && tankstate != CarState.EMPTY) {
            //cant turn off car in motion, but if the tank is empty it has to stop anyway.
            throw new IllegalStateException("Car is in motion, cannot stop engine.");
        }
        if (carstate == CarState.OFF) {
            //its already off, duh.
            throw new IllegalStateException("Car is already off, cannot turn off");
        }
        carstate = CarState.OFF;
    }

    public void fillTank(int amount) {
        if (amount + fuellevel > MAXFUEL) {
            //cant fill a tank beyond maximum
            throw new IllegalArgumentException("Tank is either full or cannot be filled past its maximum capacity.");
        }
        fuellevel += amount;
    }

    private void engineTick() {
        if (carstate == CarState.ON) {
            if (fuellevel > 0.01) {
                fuellevel -= 0.01;
            }
            else {
                fuellevel = 0; //Prevents fuel from going negative
            }
            String lineout = String.format("\rFuel Level: %s gal", fuellevel);
            System.out.print(lineout);
        }
    }

    public void runUntilEmpty(int rpm) throws InterruptedException {
        long rps = rpm / 60;
        while (fuellevel > 0) {
            engineTick();
            Thread.sleep(1000 / rps);
        }
    }
}
