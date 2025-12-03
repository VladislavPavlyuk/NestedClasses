package nested.models;

import nested.interfaces.IGame;

public class Game implements IGame {

    public enum Genre {
        ACTION, SPORT, RACE
    }

    public enum Type {
        VIRTUAL, PHYSICAL
    }

    private final String name;
    private final Genre genre;
    private final Type type;

    private Game(String name, Genre genre, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Type getType() {
        return type;
    }

    public static class GameDisk implements nested.interfaces.IGameDisk {
        private final String description;
        private final Game data;

        private GameDisk(String name, Genre genre, String description) {
            this.description = description;
            this.data = new Game(name, genre, Game.Type.PHYSICAL);
        }

        public String getDescription() {
            return description;
        }

        public Game getData() {
            return data;
        }
    }

    public static class VirtualGame implements nested.interfaces.IVirtualGame {
        private int rating;
        private final Game data;

        private VirtualGame(String name, Genre genre) {
            this.rating = 0;
            this.data = new Game(name, genre, Game.Type.VIRTUAL);
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            if (rating < 0 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 0 and 5");
            }
            this.rating = rating;
        }

        public Game getData() {
            return data;
        }
    }

    public static GameDisk getDisk(String name, Genre genre, String description) {
        return new GameDisk(name, genre, description);
    }

    public static VirtualGame getVirtualGame(String name, Genre genre) {
        return new VirtualGame(name, genre);
    }

    public static VirtualGame getVirtualGame(String name, Genre genre, int rating) {
        VirtualGame game = new VirtualGame(name, genre);
        game.setRating(rating);
        return game;
    }
}

