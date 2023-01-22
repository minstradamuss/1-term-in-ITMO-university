package md2html;

import java.util.HashMap;

public class ConverterRecursive {
    private String string;
    // Хеш-таблица меток Markdown и их аналогов в html
    private final HashMap<String, String> codes = new HashMap<String, String>() {{
        put("`", "code");
        put("*", "em");
        put("_", "em");
        put("**", "strong");
        put("__", "strong");
        put("--", "s");
        put("+", "u");
        put("++", "u");
        put("%", "var");
    }};

    // Список заголовков
    private final String[] headers = {"# ", "## ", "### ", "#### ", "##### ", "###### "};

    public String toString() {
        return string;
    }

    // Конструктор класса
    public ConverterRecursive(String value) {
        string = value;

        convertSpecialChars();
        string = processString(string, false, false).toString();
    }

    // Замена специальных символов
    private void convertSpecialChars() {
        string = string.replaceAll("&", "&amp;");
        string = string.replaceAll("<", "&lt;");
        string = string.replaceAll(">", "&gt;");
    }

    // Поиск закрывающей метки
    private int findMarkEnd(String str, String mark, int start) {
        str += " ";
        int index = 0;
        while (true) {
            index = str.indexOf(mark, start);

            if (index == -1) return -1;

            // Если перед меткой стоит слеш, продолжаем поиск
            if (str.charAt(index - 1) == '\\') {
                start = index + mark.length();
                continue;
            }

            if (mark.length() == 2) return index;
            // Если метка длинной в один символ, проверяем соседний символ
            // (Например: *fdsfdsf**, здесь выделение отсутствует)
            if (str.charAt(index + 1) != mark.charAt(0)) return index;
            else
                start = index + 2;
        }
    }

    // Рекурсивная обработка строки
    private StringBuilder processString(String str, boolean isHeader, boolean isParagraph) {
        StringBuilder res = new StringBuilder();

        // Проверяем, начинается ли строка с заголовка
        for (String header : headers) {
            // Если да, то заворачиваем его в тег и рекурсивно обрабатываем остальной текст
            if (str.startsWith(header)) {
                res.append("<h" + (header.length() - 1) + ">");
                // Во втором аргументе указываем true, чтобы не создавать параграф
                res.append(processString(str.substring(header.length()), true, false));
                res.append("</h" + (header.length() - 1) + ">");
                return res;
            }
        }

        // Если строка не заголовок и не параграф, заворачиваем его в параграф
        if (!isHeader && !isParagraph) {
            res.append("<p>");
        }

        // Проходимся по всей строке и проверяем символы
        for (int i = 0; i < str.length(); i++) {
            String ch1 = "", ch2 = "";
            if (i < str.length() - 1)
                ch2 = str.substring(i, i + 2);
            ch1 = str.substring(i, i + 1);

            String mark = "";
            // Сначала проверяем метки из двух символов (**, ++), потом метки из одного символа
            if (!ch2.isEmpty() && codes.containsKey(ch2)) mark = ch2;
            if (mark.isEmpty() && codes.containsKey(ch1)) mark = ch1;

            // Если метка найдена и перед ней стоит \, добавляем ее в итоговую строку без слеша
            if (!mark.isEmpty() && i > 0 && str.charAt(i - 1) == '\\') {
                res.append(str.substring(0, i - 1));
                res.append(mark);
                str = str.substring(i + mark.length());
                i = -1;
                continue;
            }

            if (!mark.isEmpty()) {
                // Находим закрывающую метку
                int end = findMarkEnd(str, mark, i + mark.length());
                // Если метка не закрывается
                if (end == -1) {
                    // Просто добавляем ее к итоговой строке
                    res.append(str.substring(0, i + mark.length()));
                    str = str.substring(i + mark.length());
                    i = -1;
                } else {
                    // Иначе - заворачиваем в соответствующий тег и рекурсивно обрабатываем текст внутри
                    res.append(str.substring(0, i));
                    res.append("<" + codes.get(mark) + ">");
                    res.append(processString(str.substring(i + mark.length(), end), false, true));
                    res.append("</" + codes.get(mark) + ">");
                    str = str.substring(end + mark.length());
                    i = -1;
                }
            }
        }

        // Добавляем остаток от исходной строки
        res.append(str);

        // Закрываем параграф
        if (!isHeader && !isParagraph) {
            res.append("</p>");
        }

        return res;
    }
}
