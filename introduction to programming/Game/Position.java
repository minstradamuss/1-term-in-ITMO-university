package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    int getHeight();

    int getWidth();

    boolean isValid(Move move);

    Cell getCell(int r, int c);
}
