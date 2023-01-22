package game;

public class GameMatch {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final UI ui;

    public GameMatch(UI ui, Board board, Player player1, Player player2) {
        this.ui = ui;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Cell play(int wins) {
        int score1 = 0;
        int score2 = 0;
        int gameNum = 0;
        Cell firstTurn = Cell.X;
        while (score1 < wins && score2 < wins) {
            gameNum += 1;
            ui.out.println("Игра №" + gameNum);

            Game game = new Game(false, player1, player2);
            board.restart(firstTurn);
            Cell result = game.play(board);
            if(result == Cell.X) {
                ui.out.println("В игре одержал победу игрок №1 (X)");
                score1 += 1;
            } else if (result == Cell.O) {
                ui.out.println("В игре одержал победу игрок №2 (O)");
                score2 += 1;
            } else {
                ui.out.println("Ничья!");
            }
            ui.out.println(String.format("Счёт %d:%d", score1, score2));
            firstTurn = firstTurn.getOpponent();
        }
        if (score1 > score2)
            return Cell.X;
        else
            return Cell.O;
    }
}
