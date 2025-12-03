package nested.interfaces;

import nested.enums.Brand;
import nested.enums.Color;

public interface IGamepad extends Powered {
    boolean isOn();
    double getChargeLevel();
    void decreaseBattery();
    Brand getBrand();
    String getConsoleSerial();
    int getConnectedNumber();
    Color getColor();
}

