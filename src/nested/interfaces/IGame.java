package nested.interfaces;

import nested.enums.Type;
import nested.models.Game;

public interface IGame {
    String getName();
    Game.Genre getGenre();
    Type getType();
}

