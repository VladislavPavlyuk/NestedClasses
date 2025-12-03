package nested.models;

import nested.enums.Brand;
import nested.enums.Color;
import nested.interfaces.IGameConsole;

public class GameConsole implements IGameConsole {
    private final Brand brand;
    private String model;
    private final String serial;
    private Gamepad firstGamepad;
    private Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.serial = serial;
        this.model = "Default Model";
        this.firstGamepad = new Gamepad(brand, 1);
        this.secondGamepad = new Gamepad(brand, 2);
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public void setFirstGamepad(Gamepad firstGamepad) {
        this.firstGamepad = firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public void setSecondGamepad(Gamepad secondGamepad) {
        this.secondGamepad = secondGamepad;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Game getActiveGame() {
        return activeGame;
    }

    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }

    public int getWaitingCounter() {
        return waitingCounter;
    }

    public void setWaitingCounter(int waitingCounter) {
        this.waitingCounter = waitingCounter;
    }

    @Override
    public void powerOn() {
        isOn = true;
        System.out.println("Console powered on.");
    }

    @Override
    public void powerOff() {
        isOn = false;
        System.out.println("Console powered off.");
    }

    public void joystickPoweredOn(Gamepad gamepad) {
        if (!isOn) {
            powerOn();
        }
        if (gamepad == firstGamepad && !secondGamepad.isOn()) {
            secondGamepad = firstGamepad;
            firstGamepad = gamepad;
        }
    }

    public void joystickPoweredOff(Gamepad gamepad) {
        if (gamepad == firstGamepad) {
            firstGamepad = secondGamepad;
            secondGamepad = gamepad;
        }
    }

    public void loadGame(Game game) {
        activeGame = game;
        System.out.println("Game " + game.getName() + " is loading");
    }

    public void playGame() {
        if (activeGame == null) {
            System.out.println("No game loaded.");
            return;
        }
        checkStatus();
        System.out.println("Playing " + activeGame.getName());
        if (firstGamepad.isOn()) {
            System.out.println("First gamepad charge: " + firstGamepad.getChargeLevel() + "%");
            firstGamepad.decreaseBattery();
        }
        if (secondGamepad.isOn()) {
            System.out.println("Second gamepad charge: " + secondGamepad.getChargeLevel() + "%");
            secondGamepad.decreaseBattery();
        }
    }

    private void checkStatus() {
        if (!firstGamepad.isOn() && !secondGamepad.isOn()) {
            System.out.println("Connect a gamepad");
            waitingCounter++;
            if (waitingCounter > 5) {
                powerOff();
                throw new RuntimeException("Console is shutting down due to inactivity");
            }
        } else {
            waitingCounter = 0;
        }
    }

    public class Gamepad implements nested.interfaces.IGamepad {
        private final Brand brand;
        private final String consoleSerial;
        private final int connectedNumber;
        private final Color color;
        private double chargeLevel;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.connectedNumber = connectedNumber;
            this.consoleSerial = GameConsole.this.serial;
            this.color = connectedNumber == 1 ? Color.BLACK : Color.WHITE;
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

        public void setOn(boolean on) {
            isOn = on;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
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

}
