package softaria;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PagesState {
    private final Map<String, String> pages;

    public PagesState(Map<String, String> pages) {
        this.pages = new HashMap<>();
        this.pages.putAll(pages);
    }

    public String getPageHtmlByUrl(String url) {
        return pages.get(url);
    }

    public Set<String> getPagesUrls() {
        return pages.keySet();
    }

    public boolean hasPage(String url) {
        return pages.containsKey(url);
    }
}
