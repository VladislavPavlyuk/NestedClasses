package nested.interfaces;

import nested.models.Game;

public interface IGame {
    String getName();
    Game.Genre getGenre();
    Game.Type getType();
}

