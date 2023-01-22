package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Cell play(Board board) {
        while (true) {
            Player player = switch(board.getTurn()) {
                case X -> player1;
                case O -> player2;
                default -> throw new IllegalStateException();
            };
            final Cell result = move(board, player, board.getTurn());
            if (result == null)
                continue;
            if (result == Cell.E)
                return null;
            return result;
        }
    }

    private Cell move(final Board board, final Player player, final Cell no) {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return no.getOpponent();
        } else if (result == Result.DRAW) {
            log("Draw");
            return Cell.E;
        } else {
            return null;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
