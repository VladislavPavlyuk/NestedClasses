package nested.models;

import nested.enums.Brand;
import nested.enums.Color;
import nested.interfaces.Powered;

public class Gamepad implements Powered {
    private final Brand brand;
    private final String consoleSerial;
    private final int connectedNumber;
    private final Color color;
    private double chargeLevel;
    private boolean isOn;

    public Gamepad(Brand brand, String consoleSerial, int connectedNumber, Color color) {
        this.brand = brand;
        this.consoleSerial = consoleSerial;
        this.connectedNumber = connectedNumber;
        this.color = color;
        this.chargeLevel = 100.0;
    }

    @Override
    public void powerOn() {
        isOn = true;
        joystickPoweredOn(this);
    }

    @Override
    public void powerOff() {
        isOn = false;
        joystickPoweredOff(this);
    }

    public boolean isOn() {
        return isOn;
    }

    public double getChargeLevel() {
        return chargeLevel;
    }

    public void decreaseBattery() {
        if (chargeLevel > 0) {
            chargeLevel -= 10;
            if (chargeLevel == 0) {
                powerOff();
            }
        }
    }

    public Brand getBrand() {
        return brand;
    }

    public String getConsoleSerial() {
        return consoleSerial;
    }

    public int getConnectedNumber() {
        return connectedNumber;
    }

    public Color getColor() {
        return color;
    }
}
