package nested;

import nested.models.Game;
import nested.models.GameConsole;
import nested.enums.Brand;

import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы с классами Game ===\n");
        
        System.out.println("1. Создание массива физических игр:");
        Game.GameDisk[] physicalGames = {
                Game.getDisk("Game1", Game.Genre.ACTION, "Description1"),
                Game.getDisk("Game2", Game.Genre.SPORT, "Description2"),
                Game.getDisk("Game3", Game.Genre.RACE, "Description3"),
                Game.getDisk("Game4", Game.Genre.ACTION, "Description4")
        };
        
        System.out.println("   Создано " + physicalGames.length + " физических игр");
        for (Game.GameDisk disk : physicalGames) {
            System.out.println("   - " + disk.getData().getName() + 
                             " (" + disk.getData().getGenre() + 
                             ", " + disk.getData().getType() + 
                             "): " + disk.getDescription());
        }
        
        System.out.println("\n2. Создание массива виртуальных игр:");
        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("Game5", Game.Genre.ACTION, 5),
                Game.getVirtualGame("Game6", Game.Genre.SPORT, 4),
                Game.getVirtualGame("Game7", Game.Genre.RACE, 3),
                Game.getVirtualGame("Game8", Game.Genre.ACTION, 2)
        };
        
        System.out.println("   Создано " + virtualGames.length + " виртуальных игр");
        for (Game.VirtualGame vGame : virtualGames) {
            System.out.println("   - " + vGame.getData().getName() + 
                             " (" + vGame.getData().getGenre() + 
                             ", " + vGame.getData().getType() + 
                             "): рейтинг " + vGame.getRating());
        }
        
        System.out.println("\n3. Изменение рейтинга виртуальной игры:");
        System.out.println("   Рейтинг Game5 до изменения: " + virtualGames[0].getRating());
        virtualGames[0].setRating(4);
        System.out.println("   Рейтинг Game5 после изменения: " + virtualGames[0].getRating());
        
        System.out.println("\n4. Сортировка физических игр по жанру:");
        System.out.println("   До сортировки:");
        for (Game.GameDisk disk : physicalGames) {
            System.out.println("   - " + disk.getData().getName() + ": " + disk.getData().getGenre());
        }
        
        Arrays.sort(physicalGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk a, Game.GameDisk b) {
                return a.getData().getGenre().compareTo(b.getData().getGenre());
            }
        });
        
        System.out.println("   После сортировки:");
        for (Game.GameDisk disk : physicalGames) {
            System.out.println("   - " + disk.getData().getName() + ": " + disk.getData().getGenre());
        }
        
        System.out.println("\n5. Сортировка виртуальных игр по рейтингу:");
        System.out.println("   До сортировки:");
        for (Game.VirtualGame vGame : virtualGames) {
            System.out.println("   - " + vGame.getData().getName() + ": рейтинг " + vGame.getRating());
        }
        
        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame a, Game.VirtualGame b) {
                return Integer.compare(a.getRating(), b.getRating());
            }
        });
        
        System.out.println("   После сортировки:");
        for (Game.VirtualGame vGame : virtualGames) {
            System.out.println("   - " + vGame.getData().getName() + ": рейтинг " + vGame.getRating());
        }
        
        System.out.println("\n=== Демонстрация работы с GameConsole ===\n");
        
        System.out.println("6. Создание игровой консоли:");
        GameConsole console = new GameConsole(Brand.Sony, "XC123QeWR");
        System.out.println("   Консоль создана: " + console.getBrand() + 
                         ", серийный номер: " + console.getSerial() + 
                         ", модель: " + console.getModel());
        System.out.println("   Состояние консоли: " + (console.isOn() ? "включена" : "выключена"));
        
        System.out.println("\n7. Демонстрация работы с джойстиками:");
        GameConsole.Gamepad firstGamepad = console.getFirstGamepad();
        GameConsole.Gamepad secondGamepad = console.getSecondGamepad();
        
        System.out.println("   Первый джойстик:");
        System.out.println("   - Бренд: " + firstGamepad.getBrand());
        System.out.println("   - Цвет: " + firstGamepad.getColor());
        System.out.println("   - Номер подключения: " + firstGamepad.getConnectedNumber());
        System.out.println("   - Серийный номер консоли: " + firstGamepad.getConsoleSerial());
        System.out.println("   - Заряд: " + firstGamepad.getChargeLevel() + "%");
        System.out.println("   - Состояние: " + (firstGamepad.isOn() ? "включен" : "выключен"));
        
        System.out.println("   Второй джойстик:");
        System.out.println("   - Бренд: " + secondGamepad.getBrand());
        System.out.println("   - Цвет: " + secondGamepad.getColor());
        System.out.println("   - Номер подключения: " + secondGamepad.getConnectedNumber());
        System.out.println("   - Заряд: " + secondGamepad.getChargeLevel() + "%");
        System.out.println("   - Состояние: " + (secondGamepad.isOn() ? "включен" : "выключен"));
        
        System.out.println("\n8. Включение первого джойстика (консоль должна включиться автоматически):");
        firstGamepad.powerOn();
        System.out.println("   Состояние консоли после включения джойстика: " + 
                         (console.isOn() ? "включена" : "выключена"));
        
        System.out.println("\n9. Загрузка игры:");
        console.loadGame(physicalGames[0].getData());
        System.out.println("   Активная игра: " + 
                         (console.getActiveGame() != null ? console.getActiveGame().getName() : "нет"));
        
        System.out.println("\n10. Игра в игру (демонстрация уменьшения заряда):");
        for (int i = 0; i < 3; i++) {
            System.out.println("\n   Попытка " + (i + 1) + ":");
            console.playGame();
            System.out.println("   Заряд первого джойстика после игры: " + 
                             firstGamepad.getChargeLevel() + "%");
        }
        
        System.out.println("\n11. Включение второго джойстика:");
        secondGamepad.powerOn();
        System.out.println("   Заряд второго джойстика: " + secondGamepad.getChargeLevel() + "%");
        
        System.out.println("\n12. Игра с двумя джойстиками:");
        console.playGame();
        
        System.out.println("\n13. Выключение первого джойстика (второй должен стать первым):");
        GameConsole.Gamepad oldFirst = console.getFirstGamepad();
        oldFirst.powerOff();
        System.out.println("   Новый первый джойстик - номер подключения: " + 
                         console.getFirstGamepad().getConnectedNumber());
        System.out.println("   Новый второй джойстик - номер подключения: " + 
                         console.getSecondGamepad().getConnectedNumber());
        
        System.out.println("\n14. Изменение модели консоли:");
        System.out.println("   Модель до изменения: " + console.getModel());
        console.setModel("PS5 Pro");
        System.out.println("   Модель после изменения: " + console.getModel());
        
        System.out.println("\n15. Выключение консоли:");
        console.powerOff();
        System.out.println("   Состояние консоли: " + (console.isOn() ? "включена" : "выключена"));
        
        System.out.println("\n16. Загрузка виртуальной игры:");
        console.loadGame(virtualGames[0].getData());
        console.getFirstGamepad().powerOn();
        console.playGame();
        
        System.out.println("\n=== Демонстрация завершена ===");
    }
}