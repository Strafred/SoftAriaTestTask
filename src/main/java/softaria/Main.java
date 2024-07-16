package softaria;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> pages = new HashMap<>();
        pages.put("home", "Home page");
        pages.put("about", "About page");
        pages.put("contact", "Contact page");

        PagesState yesterdayState = new PagesState(pages);

        pages.put("home", "New home page");
        pages.remove("about");
        pages.remove("contact");
        pages.put("services", "Services page");

        PagesState todayState = new PagesState(pages);

        PagesHistoryDetector pagesHistoryDetector = new PagesHistoryDetector(yesterdayState, todayState);
        pagesHistoryDetector.detectHistory();

        ReportPrinter.print(
                pagesHistoryDetector.getDeletedPages(),
                pagesHistoryDetector.getAddedPages(),
                pagesHistoryDetector.getUpdatedPages()
        );
    }
}