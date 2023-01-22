package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public enum Cell {
    X, O, E;

    public Cell getOpponent() {
        return switch (this) {
            case X -> O;
            case O -> X;
            default -> throw new IllegalStateException();
        };
    }
}
