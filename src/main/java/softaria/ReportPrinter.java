package softaria;

import java.util.List;

public class ReportPrinter {
    public static void print(List<String> deletedPages, List<String> addedPages, List<String> updatedPages) {
        StringBuilder output = new StringBuilder();
        output.append("""
                Здравствуйте, дорогая и.о. секретаря
                                
                За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                                
                Исчезли следующие страницы:
                """);
        deletedPages.forEach(url -> output.append(url).append("\n"));
        output.append("Появились следующие новые страницы:\n");
        addedPages.forEach(url -> output.append(url).append("\n"));
        output.append("Изменились следующие страницы:\n");
        updatedPages.forEach(url -> output.append(url).append("\n"));

        output.append("""
                                
                С уважением,
                автоматизированная система
                мониторинга.
                """);

        System.out.println(output);
    }
}
