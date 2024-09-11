package nested;
import nested.enums.Brand;
import nested.models.Game;

import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {
    public static void main(String[] args) {
        Game.GameDisk[] physicalGames = {
                Game.getDisk("Game1", Game.Genre.ACTION, "Description1"),
                Game.getDisk("Game2", Game.Genre.SPORT, "Description2"),
                Game.getDisk("Game3", Game.Genre.RACE, "Description3"),
                Game.getDisk("Game4", Game.Genre.ACTION, "Description4")
        };

        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("Game5", Game.Genre.ACTION, 5),
                Game.getVirtualGame("Game6", Game.Genre.SPORT, 4),
                Game.getVirtualGame("Game7", Game.Genre.RACE, 3),
                Game.getVirtualGame("Game8", Game.Genre.ACTION, 2)
        };

        // Сортировка физических игр по жанру
        Arrays.sort(physicalGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk a, Game.GameDisk b) {
                return a.getData().getGenre().compareTo(b.getData().getGenre());
            }
        });

        // Сортировка виртуальных игр по рейтингу
        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame a, Game.VirtualGame b) {
                return Integer.compare(a.getRating(), b.getRating());
            }
        });

        // Пример использования GameConsole
        GameConsole console = new GameConsole(Brand.SONY,"222");
        console.loadGames(physicalGames, virtualGames);
    }
}