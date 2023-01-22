package md2html;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Md2Html {
    public static void main(String[] args) {

        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));) {
            String line = in.readLine();
            // Считываем строки, пока параграф не закончится
            while (line != null) {

                StringBuilder currentParagraph = new StringBuilder(); // Текущий параграф
                int emptyLines = 0; // Счетчик пустых строк
                while (line != null) {
                    if (!line.isEmpty()) {
                        currentParagraph.append(line).append('\n');
                        emptyLines = 0;
                    } else if (currentParagraph.length() > 0) {
                        emptyLines++;
                    }
                    if (emptyLines > 0) {
                        break;
                    }
                    line = in.readLine();
                }
                // Конвертируем строку и записываем в файл
                if (currentParagraph.length() > 0) {
                    currentParagraph.deleteCharAt(currentParagraph.length() - 1);
                    ConverterRecursive conv = new ConverterRecursive(currentParagraph.toString());
                    out.write(conv.toString() + '\n');
                }
                line = in.readLine();
            }
        } catch(FileNotFoundException e) {
            System.err.println("File not found " + e.getMessage());
        } catch(IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}