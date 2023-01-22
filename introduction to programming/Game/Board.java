package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Position getPosition();
    Cell getTurn();
    Result makeMove(Move move);
    void restart(Cell firstTurn);
}
