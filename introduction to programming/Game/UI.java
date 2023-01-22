package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UI {
    public final PrintStream out;
    public final Scanner in;

    public UI() {
        this(System.out, new Scanner(System.in));
    }

    public UI(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public Player inputPlayer(String prompt) {
        while (true) {
            out.println(prompt);
            String str = in.next();
            Player player = switch (str.toLowerCase(Locale.ROOT)) {
                case "human" -> new HumanPlayer(this);
                case "random" -> new RandomPlayer();
                case "sequential" -> new SequentialPlayer();
                default -> null;
            };
            if (player != null)
                return player;
            out.println("Ошибка ввода. Нужно ввести одно из: Human, Random, Sequential");
        }

    }

    public int inputInteger(String prompt) {
        return inputInteger(prompt, 0, Integer.MAX_VALUE);
    }

    public int inputInteger(String prompt, int min) {
        return inputInteger(prompt, min, Integer.MAX_VALUE);
    }

    public int inputInteger(String prompt, int min, int max) {
        while (true) {
            if (prompt != null)
                out.println(prompt);
            try {
                int result = in.nextInt();
                if (min <= result && result <= max)
                    return result;
            } catch (InputMismatchException exception) {
                out.println("Нужно целое число. Повторите ввод.");
            }
            out.println(String.format("Нужно целое число в диапазоне %d-%d. Повторите ввод.", min, max));
        }
    }
}
