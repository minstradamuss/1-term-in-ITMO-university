package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private final int width;
    private final int height;
    private final int goal;
    private Cell turn;
    private int emptyCells;

    public MNKBoard(int height, int width, int goal) {
        this.height = height;
        this.width = width;
        this.goal = goal;
        this.cells = new Cell[height][width];
        restart(Cell.X);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public int getGoal() {
        return goal;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        emptyCells -= 1;

        int length = getLongest(move);
        if (length >= getGoal())
            return Result.WIN;
        if (emptyCells <= 0)
            return Result.DRAW;
        if (length < getRepeatMoveThreshold())
            turn = turn.getOpponent();
        return Result.UNKNOWN;
    }

    @Override
    public void restart(Cell firstTurn) {
        emptyCells = height * width;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = firstTurn;
    }

    private int getRepeatMoveThreshold() {
        return 4;
    }

    private int getLongest(Move move) {
        int best = 1;
        best = Math.max(best, getLongest(move, 1, 0));
        best = Math.max(best, getLongest(move, 0, 1));
        best = Math.max(best, getLongest(move, 1, 1));
        best = Math.max(best, getLongest(move, 1, -1));
        return best;
    }

    private int getLongest(Move move, int deltax, int deltay) {
        return getLongestInDirection(move, deltax, deltay) + getLongestInDirection(move, -deltax, -deltay) - 1;
    }

    private int getLongestInDirection(Move move, int deltax, int deltay) {
        int r = move.getRow();
        int c = move.getColumn();
        int result = 0;
        try {
            while (getCell(r, c) == move.getValue()) {
                r += deltax;
                c += deltay;
                result += 1;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
        }
        return result;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < height
                && 0 <= move.getColumn() && move.getColumn() < width
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getTurn();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int rowTitleCol = (int) Math.ceil(Math.log10(height));
        final StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%" + rowTitleCol + "s ", ""));
        for (int c = 0; c < getWidth(); c++) {
            sb.append(c % 10);
        }
        for (int r = 0; r < height; r++) {
            sb.append("\n");
            sb.append(String.format("%" + rowTitleCol + "d ", r));
            for (int c = 0; c < width; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
