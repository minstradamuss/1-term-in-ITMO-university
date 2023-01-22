package game;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final UI ui;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this(new UI(out, in));
    }

    public HumanPlayer() {
        this(new UI());
    }

    public HumanPlayer(final UI ui) { this.ui = ui; }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            ui.out.println("Position");
            ui.out.println(position);
            ui.out.println(cell + "'s move");
            ui.out.println("Enter row and column");
            final Move move = new Move(ui.in.nextInt(), ui.in.nextInt(), cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            ui.out.println("Move " + move + " is invalid");
        }
    }
}
