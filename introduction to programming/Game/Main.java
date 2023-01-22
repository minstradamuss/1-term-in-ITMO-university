package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        int height = ui.inputInteger("Введите размер доски по вертикали: M=", 2);
        int width = ui.inputInteger("Введите размер доски по горизонтали: N=", 2);
        int goal = ui.inputInteger("Введите длину линии для победы: K=", 2, Math.max(height, width));
        Player player1 = ui.inputPlayer("Первый (X) игрок (Human, Random или Sequential):");
        Player player2 = ui.inputPlayer("Второй (O) игрок (Human, Random или Sequential):");

        int wins = ui.inputInteger("Число очков для победы в матче:", 1);

        MNKBoard board = new MNKBoard(height, width, goal);
        final GameMatch match = new GameMatch(ui, board, player1, player2);
        Cell result = match.play(wins);
        ui.out.println("В матче победил игрок: " + result);
    }
}
