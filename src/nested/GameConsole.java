package nested;

public class GameConsole {
    // Enum для бренда
    public enum Brand {
        SONY, MICROSOFT, NINTENDO
    }

    // Поля класса
    private Brand brand;
    private String model;
    private String serial;
    private Gamepad firstGamepad;
    private Gamepad secondGamepad;
    private boolean isOn;

    // Внутренний класс для джойстика
    public class Gamepad {
        private String type;

        public Gamepad(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    // Конструктор
    public GameConsole(Brand brand, String model, String serial) {
        this.brand = brand;
        this.model = model;
        this.serial = serial;
        this.firstGamepad = new Gamepad("First");
        this.secondGamepad = new Gamepad("Second");
        this.isOn = false;
    }

    // Методы для включения и выключения консоли
    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    // Геттеры и сеттеры
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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

    public void setSerial(String serial) {
        this.serial = serial;
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

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }
}
