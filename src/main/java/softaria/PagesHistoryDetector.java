package softaria;

import java.util.ArrayList;
import java.util.List;

public class PagesHistoryDetector {
    private final PagesState firstState;
    private final PagesState secondState;

    private final List<String> deletedPages;
    private final List<String> addedPages;
    private final List<String> updatedPages;

    public PagesHistoryDetector(PagesState firstState, PagesState secondState) {
        this.firstState = firstState;
        this.secondState = secondState;

        deletedPages = new ArrayList<>();
        addedPages = new ArrayList<>();
        updatedPages = new ArrayList<>();
    }

    public void detectHistory() {
        firstState.getPagesUrls().forEach(url -> {
            if (!secondState.hasPage(url)) {
                deletedPages.add(url);
            } else if (!firstState.getPageHtmlByUrl(url).equals(secondState.getPageHtmlByUrl(url))) {
                updatedPages.add(url);
            }
        });

        secondState.getPagesUrls().forEach(url -> {
            if (!firstState.hasPage(url)) {
                addedPages.add(url);
            }
        });
    }


    public List<String> getDeletedPages() {
        return deletedPages;
    }

    public List<String> getAddedPages() {
        return addedPages;
    }

    public List<String> getUpdatedPages() {
        return updatedPages;
    }
}