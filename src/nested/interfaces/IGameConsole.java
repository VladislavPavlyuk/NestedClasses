package nested.interfaces;

import nested.models.Game;
import nested.models.GameConsole;

public interface IGameConsole extends Powered {
    void joystickPoweredOn(GameConsole.Gamepad gamepad);
    void joystickPoweredOff(GameConsole.Gamepad gamepad);
    void loadGame(Game game);
    void playGame();
}



