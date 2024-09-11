package nested.models;

import nested.enums.Brand;
import nested.enums.Color;
import nested.interfaces.Powered;

class GameConsole implements Powered {
    private final Brand brand;
    private final String model;
    private final String serial;
    private Gamepad firstGamepad;
    private Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.model = "Default Model"; // Можно изменить на нужное значение
        this.serial = serial;
        this.firstGamepad = new Gamepad(brand, serial, 1, Color.BLACK);
        this.secondGamepad = new Gamepad(brand, serial, 2, Color.WHITE);
    }
    public void loadGames(Game.GameDisk[] physicalGames, Game.VirtualGame[] virtualGames) {
        // Логика загрузки игр в консоль
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
        if (gamepad == firstGamepad && secondGamepad.isOn()) {
            firstGamepad = secondGamepad;
            secondGamepad = gamepad;
        }
    }

    public void loadGame(Game game) {
        activeGame = game;
        System.out.println("Игра " + game.getName() + " загружается");
    }

    public void playGame() {
        if (activeGame == null) {
            System.out.println("Нет загруженной игры.");
            return;
        }
        checkStatus();
        System.out.println("Играем в " + activeGame.getName());
        if (firstGamepad.isOn()) {
            System.out.println("Заряд первого джойстика: " + firstGamepad.getChargeLevel() + "%");
            firstGamepad.decreaseBattery();
        }
        if (secondGamepad.isOn()) {
            System.out.println("Заряд второго джойстика: " + secondGamepad.getChargeLevel() + "%");
            secondGamepad.decreaseBattery();
        }
    }

    private void checkStatus() {
        if (!firstGamepad.isOn() && !secondGamepad.isOn()) {
            System.out.println("Подключите джойстик");
            waitingCounter++;
            if (waitingCounter > 5) {
                powerOff();
                throw new RuntimeException("Приставка завершает работу из-за отсутствия активности");
            }
        } else {
            waitingCounter = 0;
        }
    }
}
